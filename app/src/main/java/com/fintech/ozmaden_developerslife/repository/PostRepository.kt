package com.fintech.ozmaden_developerslife.repository

import com.fintech.ozmaden_developerslife.api.RetrofitInstance
import com.fintech.ozmaden_developerslife.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
    suspend fun getRandomPost(): Post? {
//        return RetrofitInstance.api.getRandomPost()
        var post: Post? = null
        withContext(Dispatchers.IO) {
            post = RetrofitInstance.api.getRandomPost()
        }
        return post
    }

    fun getCategoryPosts(category: String, page: Int): Collection<Post>? {
        return RetrofitInstance.api.getCategoryPosts(category, page).result
    }
}