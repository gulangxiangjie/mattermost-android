package com.mattermost.torry.net;

import com.mattermost.torry.CoreApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

  public static final String BASE_URL = "http://192.168.3.7:8065/";
  private static Api instance;
  private Retrofit retrofit;
  private static volatile PostService mPostService;

  private String loginedUserId = "93ww8p6pp7g9jdeuf3nfap4uqr";

  private Api(){}

  public static Api getInstance() {
    if(instance == null) {
      synchronized (Api.class) {
        if(instance == null) {
          instance = new Api();
          instance.retrofit = new Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .client(CoreApplication.mOkHttpClient)
                  .addConverterFactory(GsonConverterFactory.create())
                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                  .build();
        }
      }
    }
    return instance;
  }

  public PostService postService() {
    if(mPostService == null) {
      synchronized (PostService.class) {
        mPostService = retrofit.create(PostService.class);
      }
    }
    return mPostService;
  }

  public boolean isMe(String userId) {
    return loginedUserId.equals(userId);
  }

}
