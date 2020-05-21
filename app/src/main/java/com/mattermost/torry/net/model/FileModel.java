package com.mattermost.torry.net.model;

import com.google.gson.annotations.SerializedName;

public class FileModel extends MetadataModel {

  public String name;
  public String extension;
  public int size;
  @SerializedName("mime_type")
  public String mimeType;
  public int width;
  public int height;
  @SerializedName("has_preview_image")
  public boolean hasPreviewImage;

}
