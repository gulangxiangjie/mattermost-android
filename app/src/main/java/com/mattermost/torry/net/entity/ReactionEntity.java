package com.mattermost.torry.net.entity;

import com.google.gson.annotations.SerializedName;

public class ReactionEntity extends MetadataEntity {

  @SerializedName("emoji_name")
  public String emojiName;

}
