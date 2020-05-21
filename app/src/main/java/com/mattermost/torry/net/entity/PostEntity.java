package com.mattermost.torry.net.entity;

import android.util.ArrayMap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostEntity {

  public String id;
  @SerializedName("create_at")
  public long createAt;
  @SerializedName("update_at")
  public long updateAt;
  @SerializedName("delete_at")
  public long deleteAt;
  @SerializedName("edit_at")
  public long editAt;
  @SerializedName("user_id")
  public String userId;
  @SerializedName("channel_id")
  public String channelId;
  @SerializedName("root_id")
  public String rootId;
  @SerializedName("parent_id")
  public String parentId;
  @SerializedName("original_id")
  public String originalId;
  public String message;
  public String type;
  public String hashtag;
  @SerializedName("pending_post_id")
  public String pendingPostId;
  public ArrayMap<String, String> props;
  public List<String> filenames;
  @SerializedName("file_ids")
  public List<String> fileIds;
  public ArrayMap<String, List<FileEntity>> metadata;

}
