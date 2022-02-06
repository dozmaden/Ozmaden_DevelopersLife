package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

/** ViewModel для постов, полученные из категории. */
internal class CategoryPostViewModel(private val category: String) : PostViewModel() {

    /** Для постов с из категорий необходимо учитывать нынешнню страницу с сайта */
    private var page: Int = 0

    /** Загрузка постов с некой катерогии из репозитория. */
    override fun loadNewPost() {
        try {
            postRepository
                .getCategoryPosts(category, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { posts ->
                        // постов с категорий приходят несколько
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

    /** Фактори класс для того, чтобы вернуть класс с аргументом [category]. */
    internal class Factory(private val category: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST") return CategoryPostViewModel(category) as T
        }
    }
}
