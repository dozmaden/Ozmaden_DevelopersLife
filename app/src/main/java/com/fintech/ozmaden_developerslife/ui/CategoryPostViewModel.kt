package com.fintech.ozmaden_developerslife.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.repository.PostRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

internal class CategoryPostViewModel(private val category: String) : PostViewModel() {

    private var page: Int = 0

    override fun loadNewPost() {
        PostRepository.getCategoryPosts(category, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { posts ->
                    postHistory.addAll(posts)
                    page++
                    position++
                    if (postHistory.size < position + 1) {
                        loadingFail.postValue(true)
                    } else {
                        _post.postValue(postHistory.elementAt(position))
                    }
                },
                onError = { loadingFail.postValue(true) }
            )
            .onBind()
    }

    internal class Factory(private val category: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryPostViewModel(category) as T
        }
    }
}
