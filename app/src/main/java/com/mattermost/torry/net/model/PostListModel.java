package com.mattermost.torry.net.model;

import android.util.ArrayMap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostListModel {

  public List<String> order;
  public ArrayMap<String, PostModel> posts;
  @SerializedName("next_post_id")
  public String nextPostId;
  @SerializedName("prev_post_id")
  public String prevPostId;

}
