package com.mattermost.torry.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mattermost.torry.CoreApplication;
import com.mattermost.torry.R;
import com.mattermost.torry.ui.posts.model.PostModel;
import com.squareup.picasso.Transformation;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.Holder> {

  private static final String TAG = "Kira";
  private final int VIEW_NORMAL = 0;
  private final int VIEW_IMAGE = 1;

  private List<PostModel> posts;

  public PostListAdapter(List<PostModel> posts) {
    this.posts = posts;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = null;
    if(viewType == VIEW_IMAGE) {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.post_item_file, parent, false);
    } else {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.post_item, parent, false);
    }
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    final PostModel post = posts.get(position);
    if(post != null) {
      if(post.isMe) {
        holder.ivAvatarLeft.setVisibility(View.INVISIBLE);
        holder.ivAvatarRight.setVisibility(View.VISIBLE);
        CoreApplication.mPicasso.load(post.avatar)
                .error(R.mipmap.ic_avatar_demo2)
                .transform(new RoundImageTransformation())
                .into(holder.ivAvatarRight);
      } else {
        holder.ivAvatarLeft.setVisibility(View.VISIBLE);
        holder.ivAvatarRight.setVisibility(View.INVISIBLE);
        CoreApplication.mPicasso.load(post.avatar)
                .error(R.mipmap.ic_avatar_demo2)
                .transform(new RoundImageTransformation())
                .into(holder.ivAvatarLeft);
      }
      if(holder.tvMessage != null) {
        holder.tvMessage.setText(post.message);
        holder.tvMessage.setBackgroundResource(post.bgColorResId);
        holder.tvMessage.setTextColor(holder.tvMessage.getResources().getColor(post.textColor));
      }
    }
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  @Override
  public int getItemViewType(int position) {
    PostModel post = posts.get(position);
    if(post != null && post.displayType == PostModel.TYPE_IMAGE) {
      return VIEW_IMAGE;
    }
    return VIEW_NORMAL;
  }

  static class RoundImageTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
      Log.d(TAG, "create round bitmap");
      int size = Math.min(source.getWidth(), source.getHeight());
      int x = (source.getWidth() - size) / 2;
      int y = (source.getHeight() - size) / 2;
      Log.d(TAG, "x = " + source.getWidth() + ", y = " + source.getHeight() + ", size = " + size);
      Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
      if(squaredBitmap != source) {
        source.recycle();
      }
      Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
      Canvas canvas = new Canvas(bitmap);
      Paint paint = new Paint();
      BitmapShader shader = new BitmapShader(squaredBitmap,
              BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
      paint.setShader(shader);
      paint.setAntiAlias(true);
      float r = size / 2f;
      canvas.drawCircle(r, r, r, paint);
      squaredBitmap.recycle();
      return bitmap;
    }

    @Override
    public String key() {
      return "square()";
    }
  }

  static class Holder extends RecyclerView.ViewHolder {

    ImageView ivAvatarLeft;
    ImageView ivAvatarRight;
    TextView tvMessage;

    Holder(@NonNull View view) {
      super(view);
      ivAvatarLeft = view.findViewById(R.id.avatar_left);
      ivAvatarRight = view.findViewById(R.id.avatar_right);
      tvMessage = view.findViewById(R.id.message);
    }
  }

}
