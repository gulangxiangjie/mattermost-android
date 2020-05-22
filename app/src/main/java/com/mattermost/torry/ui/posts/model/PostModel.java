package com.mattermost.torry.ui.posts.model;

import com.mattermost.torry.R;
import com.mattermost.torry.net.Api;
import com.mattermost.torry.net.entity.PostEntity;

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
  public String fileSize;
  public String fileAction;
  public int fileCardStartBgColor;
  public int fileCardEndBgColor;
  public int displayType;
  public boolean isMe;

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
    return post;
  }

}
