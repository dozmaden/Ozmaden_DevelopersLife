package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

internal class CategoryPostViewModel(private val category: String) : PostViewModel() {

    private var page: Int = 0

    override fun loadNewPost() {
        try {
            postRepository
                .getCategoryPosts(category, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { posts ->
                        postHistory.addAll(posts)
                        page++
                        position++
                        _post.postValue(postHistory.elementAt(position))
                    },
                    onError = { loadingFail.postValue(true) }
                )
                .onBind()
        } catch (e: Exception) {
            Log.w("RandomPostViewModel", e.message.toString())
            loadingFail.postValue(true)
        }
    }

    internal class Factory(private val category: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST") return CategoryPostViewModel(category) as T
        }
    }
}
