package com.mattermost.torry;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CoreApplication extends Application {

  public static Typeface TfFontIcon;
  public static Picasso mPicasso;
  public static OkHttpClient mOkHttpClient;

  @Override
  public void onCreate() {
    super.onCreate();
    TfFontIcon = Typeface.createFromAsset(getAssets(), "fonts/iconfont.ttf");
    mOkHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new Interceptor() {
              @Override
              public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .header("Authorization", "Bearer 9awt5jiq9id8ddd6uqn87ga5aw")
                        .build();
                return chain.proceed(request);
              }
            }).build();
    mPicasso = new Picasso.Builder(getApplicationContext())
            .downloader(new OkHttp3Downloader(mOkHttpClient))
            .build();
  }

}
