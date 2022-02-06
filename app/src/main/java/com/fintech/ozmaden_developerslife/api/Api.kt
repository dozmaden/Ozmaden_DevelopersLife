package com.fintech.ozmaden_developerslife.api

import com.fintech.ozmaden_developerslife.dto.Post
import com.fintech.ozmaden_developerslife.dto.PostListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface Api {
    @GET("/random?json=true") fun getRandomPost(): Single<Post>

    @GET("/{category}/{page}?json=true")
    fun getCategoryPosts(
        @Path("category") category: String,
        @Path("page") page: Int
    ): Single<PostListResponse>
}
