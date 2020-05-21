package com.mattermost.torry.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

  private static final String BASE_URL = "http://192.168.3.7:8065/";
  private static Api instance;
  private Retrofit retrofit;
  private static volatile PostService mPostService;

  private Api(){}

  public static Api getInstance() {
    if(instance == null) {
      synchronized (Api.class) {
        instance = new Api();
        instance.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
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

}
