package com.fintech.ozmaden_developerslife.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

internal class CategoryPostViewModel(
    private val category: String
) : PostViewModel() {

    private var page: Int = 0

    override fun loadNewPost() {
        postRepository.getCategoryPosts(category, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { posts ->
                postHistory.addAll(posts)
                page++
                position++
                _post.postValue(postHistory.elementAt(position))
            })
            .onBind()
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