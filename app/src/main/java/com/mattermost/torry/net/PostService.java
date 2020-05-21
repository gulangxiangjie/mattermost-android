package com.mattermost.torry.net;

import com.mattermost.torry.net.entity.PostListEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PostService {

  @GET("api/v4/channels/{channelId}/posts")
  PostListEntity getPostsForChannel(@Path("channelId") String channelId);

  @Headers({"Authorization: Bearer 9awt5jiq9id8ddd6uqn87ga5aw"})
  @GET("api/v4/channels/{channelId}/posts")
  Observable<PostListEntity> postsForChannel(@Path("channelId") String channelId);

}
