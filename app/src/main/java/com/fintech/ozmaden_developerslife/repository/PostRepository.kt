package com.fintech.ozmaden_developerslife.repository

import com.fintech.ozmaden_developerslife.api.NetworkInstance
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.model.PostsWrapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class PostRepository {
    private val api = NetworkInstance.api

    fun getRandomPost(): Single<Post> = api.getRandomPost()
        .subscribeOn(Schedulers.io())

    fun getCategoryPosts(category: String, page: Int): Single<List<Post>> =
        api.getCategoryPosts(category, page)
            .subscribeOn(Schedulers.io())
            .map(PostsWrapper::result)
}