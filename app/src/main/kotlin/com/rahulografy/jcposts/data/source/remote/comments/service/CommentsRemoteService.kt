package com.rahulografy.jcposts.data.source.remote.comments.service

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.util.Constants.Network.Api.URL_GET_COMMENTS
import com.rahulografy.jcposts.util.Constants.Network.Api.URL_PATH_POST_ID
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsRemoteService {

    @GET(URL_GET_COMMENTS)
    fun getComments(@Path(URL_PATH_POST_ID) postId: Int): Single<List<PostEntity>>
}