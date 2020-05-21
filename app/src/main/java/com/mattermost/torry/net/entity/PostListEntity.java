package com.mattermost.torry.net.entity;

import android.util.ArrayMap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostListEntity {

  public List<String> order;
  public ArrayMap<String, PostEntity> posts;
  @SerializedName("next_post_id")
  public String nextPostId;
  @SerializedName("prev_post_id")
  public String prevPostId;

}
