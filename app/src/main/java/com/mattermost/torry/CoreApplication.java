package com.mattermost.torry;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

public class CoreApplication extends Application {

  public static Typeface TfFontIcon;

  @Override
  public void onCreate() {
    super.onCreate();
    TfFontIcon = Typeface.createFromAsset(getAssets(), "fonts/iconfont.ttf");
  }

}
