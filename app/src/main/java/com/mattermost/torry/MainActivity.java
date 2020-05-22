package com.mattermost.torry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.entity.PostEntity;
import com.mattermost.torry.net.entity.PostListEntity;
import com.mattermost.torry.ui.PostListAdapter;
import com.mattermost.torry.ui.posts.model.PostModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Kira";
  private List<PostModel> posts = new ArrayList<>();

  private RecyclerView rvPosts;
  private PostListAdapter adtPosts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    rvPosts = findViewById(R.id.post_listview);
    adtPosts = new PostListAdapter(posts);
    rvPosts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
    rvPosts.setAdapter(adtPosts);

    Api.getInstance().postService()
            .postsForChannel("mfpeh3cmufngpkonoh3iake8oc")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<PostListEntity>() {
              @Override
              public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
              }

              @Override
              public void onNext(PostListEntity entity) {
                for(String key : entity.order) {
                  PostEntity p = entity.posts.get(key);
                  PostModel post = PostModel.fromEntity(p);
                  posts.add(post);
                  adtPosts.notifyDataSetChanged();
                }
              }

              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
              }

              @Override
              public void onComplete() {
                Log.d(TAG, "onComplete");
              }
            });
  }
}
