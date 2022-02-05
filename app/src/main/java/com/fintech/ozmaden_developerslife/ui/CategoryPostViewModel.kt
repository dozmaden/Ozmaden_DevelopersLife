package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryPostViewModel(
    private val category: String
) : PostViewModel() {

    private var page: Int = 0

//    init {
//        viewModelScope.launch {
//            loadNewPost()
//        }
//    }

    override suspend fun loadNewPost() {
        Log.d("CategoryPostViewModel", category)
        viewModelScope.launch {
            val newPosts = postRepository.getCategoryPosts(category, page)
            newPosts?.let {
                postHistory.addAll(newPosts)
                page++
                position++
                _post.postValue(postHistory.elementAt(position))
            }
        }
    }

    internal class Factory(
        private val category: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return CategoryPostViewModel(category) as T
        }
    }
}