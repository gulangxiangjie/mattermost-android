package com.mattermost.torry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.entity.PostListEntity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Kira";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView tv = findViewById(R.id.icon_font);
//    tv.setText(R.string.font_location);

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
              public void onNext(PostListEntity postListEntity) {
                Log.d(TAG, postListEntity.toString());
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
