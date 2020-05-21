package com.mattermost.torry.net.model;

import com.google.gson.annotations.SerializedName;

public class ReactionModel extends MetadataModel {

  @SerializedName("emoji_name")
  public String emojiName;

}
