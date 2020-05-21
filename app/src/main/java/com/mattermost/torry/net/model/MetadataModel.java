package com.mattermost.torry.net.model;

import com.google.gson.annotations.SerializedName;

public class MetadataModel {

  public String id;
  @SerializedName("user_id")
  public String userId;
  @SerializedName("post_id")
  public String postId;
  @SerializedName("create_id")
  public long createAt;
  @SerializedName("update_id")
  public long updateAt;
  @SerializedName("delete_id")
  public long deleteAt;

}
