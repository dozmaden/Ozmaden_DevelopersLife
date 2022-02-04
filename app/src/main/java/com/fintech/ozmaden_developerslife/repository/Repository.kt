package com.fintech.ozmaden_developerslife.repository

import com.fintech.ozmaden_developerslife.api.RetrofitInstance
import com.fintech.ozmaden_developerslife.model.Post

class Repository {
    fun getRandomPost(): Post? {
        return RetrofitInstance.api.getRandomPost()
    }

    fun getCategoryPosts(category: String, page: Int): Collection<Post>? {
        return RetrofitInstance.api.getCategoryPosts(category, page).result
    }
}