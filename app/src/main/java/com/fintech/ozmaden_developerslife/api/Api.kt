package com.fintech.ozmaden_developerslife.api

import com.fintech.ozmaden_developerslife.model.CategoryPosts
import com.fintech.ozmaden_developerslife.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

internal const val ENDPOINT = "https://developerslife.ru/"

interface Api {
    @GET("/random?json=true")
    suspend fun getRandomPost(): Post

    @GET("/{category}/{page}?json=true")
    suspend fun getCategoryPosts(
        @Path("category") category: String,
        @Path("page") page: Int
    ): CategoryPosts
}