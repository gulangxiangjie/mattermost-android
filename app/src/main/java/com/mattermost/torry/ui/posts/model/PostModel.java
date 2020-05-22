package com.mattermost.torry.ui.posts.model;

import com.mattermost.torry.R;
import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.entity.FileEntity;
import com.mattermost.torry.net.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class PostModel {

  public static final int TYPE_TEXT = 0;
  public static final int TYPE_FILE = 1;
  public static final int TYPE_MUSIC = 2;
  public static final int TYPE_IMAGE = 3;
  public static final int TYPE_VIDEO = 4;
  public static final int TYPE_STICKER = 5;
  public static final int TYPE_LOCATION = 6;
  public static final int TYPE_VOICE = 7;

  public String avatar;
  public String message;
  public int textColor;
  public int bgColorResId;
  public String groupTitle;
  public int leftTopRadius;
  public int leftBottomRadius;
  public int rightTopRadius;
  public int rightBottomRadius;
  public int fileIcon;
  public String fileTitle;
  public String fileSize;
  public String fileAction;
  public int fileCardStartBgColor;
  public int fileCardEndBgColor;
  public int displayType;
  public boolean isMe;
  public int imageWidth;
  public int imageHeight;
  public boolean hasPreviewImage;

  public static PostModel fromEntity(PostEntity entity) {
    PostModel post = new PostModel();
    String avatar = new StringBuilder().append(Api.BASE_URL).append("api/v4/users/")
            .append(entity.userId).append("/image").toString();
    post.avatar = avatar;
    post.message = entity.message;
    post.isMe = Api.getInstance().isMe(entity.userId);
    if(post.isMe) {
      // Post displayed on the right side
      post.bgColorResId = R.drawable.post_bubble_sent;
      post.textColor = R.color.post_message_text_color_sent;
    } else {
      // Post displayed on the left side
      post.bgColorResId = R.drawable.post_bubble_received;
      post.textColor = R.color.post_message_text_color_received;
    }
    // Parse the file infos
    if(entity.metadata != null &&
            entity.metadata.get("files") != null && entity.metadata.get("files").size() > 0) {
      List<FileEntity> fileEntities = entity.metadata.get("files");
      if(fileEntities != null && !fileEntities.isEmpty()) {
        FileEntity fileEntity = fileEntities.get(0);
        if(isImage(fileEntity.mimeType)) {
          post.displayType = TYPE_IMAGE;
          post.fileTitle = fileEntity.name;
          post.imageWidth = fileEntity.width;
          post.imageHeight = fileEntity.height;
          post.hasPreviewImage = fileEntity.hasPreviewImage;
          post.fileSize = calculateFileSize(fileEntity.size);
        }
      }
    }
    return post;
  }

  private static String calculateFileSize(int size) {
    StringBuilder sb = new StringBuilder();
    if(size < 1024) {
      sb.append(size).append(" B");
    } else if(size < 1024 * 1024) {
      sb.append(size / 1024).append(" KB");
    } else if(size < 1024 * 1024 * 1024) {
      sb.append(size / 1024 / 1024).append(" MB");
    }
    return sb.toString();
  }

  private static boolean isImage(String mimeType) {
    return "image/jpeg".equals(mimeType) ||
            "image/jpg".equals(mimeType) ||
            "image/png".equals(mimeType) ||
            "image/gif".equals(mimeType) ||
            "image/webp".equals(mimeType);
  }

}
