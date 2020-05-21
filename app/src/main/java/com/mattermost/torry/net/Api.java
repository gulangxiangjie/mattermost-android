package com.mattermost.torry.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

  private static final String BASE_URL = "http://192.168.3.7:8065/";
  private static Api instance;
  private Retrofit retrofit;

  private Api(){}

  public static Api getInstance() {
    if(instance == null) {
      instance = new Api();
      instance.retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return instance;
  }

  public PostService postService() {
    return retrofit.create(PostService.class);
  }

}
