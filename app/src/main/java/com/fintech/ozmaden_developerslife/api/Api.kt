package com.fintech.ozmaden_developerslife.api

import retrofit2.http.GET
import retrofit2.http.Path

internal const val ENDPOINT = "https://developerslife.ru/"

interface Api {
    @GET("/random?json=true")
    fun getRandomPost():
    // Post

    @GET("/{category}/{page}?json=true")
    fun getCategoryPosts(
        @Path("category") category: String,
        @Path("page") page: Int
    )
    // : CategoryPosts
}