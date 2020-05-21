package com.mattermost.torry.net;

import com.mattermost.torry.net.model.PostListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PostService {

  @GET("api/v4/channels/{channelId}/posts")
  PostListModel getPostsForChannel(@Path("channelId") String channelId);

  @Headers({"Authorization: Bearer 9awt5jiq9id8ddd6uqn87ga5aw"})
  @GET("api/v4/channels/{channelId}/posts")
  Call<PostListModel> postsForChannel(@Path("channelId") String channelId);

}
