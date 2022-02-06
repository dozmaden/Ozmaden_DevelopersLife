package com.fintech.ozmaden_developerslife.repository

import com.fintech.ozmaden_developerslife.api.NetworkInstance
import com.fintech.ozmaden_developerslife.dto.Post
import com.fintech.ozmaden_developerslife.dto.PostListResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal object PostRepository {

    private val api = NetworkInstance.api

    /** Возвращает рандомный [Post] */
    internal fun getRandomPost(): Single<Post> = api.getRandomPost().subscribeOn(Schedulers.io())

    /** Возвращает лист [Post] из выбранной категории */
    internal fun getCategoryPosts(category: String, page: Int): Single<List<Post>> =
        api.getCategoryPosts(category, page)
            .subscribeOn(Schedulers.io())
            .map(PostListResponse::result)
}
