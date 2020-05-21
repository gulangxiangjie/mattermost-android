package com.mattermost.torry.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.mattermost.torry.CoreApplication;

public class FontIcon extends androidx.appcompat.widget.AppCompatTextView {

  public FontIcon(Context context) {
    super(context);
    init();
  }

  public FontIcon(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FontIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    this.setTypeface(CoreApplication.TfFontIcon);
  }

}
