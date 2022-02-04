package com.fintech.ozmaden_developerslife.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class CategoryPostViewModel : PostViewModel() {

    private var page: Int = 0

    abstract fun category(): String

    override suspend fun loadNewPost() {
        viewModelScope.launch {
            val newPosts = postRepository.getCategoryPosts(category(), page)
            newPosts?.let {
                position++
                page++
                postHistory.addAll(newPosts)
                _post.postValue(postHistory.elementAt(position))
            }
        }
    }
}