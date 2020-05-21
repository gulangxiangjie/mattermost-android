package com.mattermost.torry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.model.PostListModel;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Kira";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Api.getInstance().postService()
            .postsForChannel("mfpeh3cmufngpkonoh3iake8oc")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<PostListModel>() {
              @Override
              public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
              }

              @Override
              public void onNext(PostListModel postListModel) {
                Log.d(TAG, postListModel.toString());
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
