package com.mattermost.torry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.model.PostListModel;

import java.io.IOException;

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
            .postsForChannel("mfpeh3cmufngpkonoh3iake8oc").enqueue(new Callback<PostListModel>() {
      @Override
      public void onResponse(Call<PostListModel> call, Response<PostListModel> response) {
        if(response.isSuccessful()) {
          Log.d(TAG, response.body().toString());
        } else {
          try {
            Log.d(TAG, response.errorBody().string());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      public void onFailure(Call<PostListModel> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}
