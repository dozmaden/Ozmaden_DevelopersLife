package com.fintech.ozmaden_developerslife.ui.random

import android.util.Log
import com.fintech.ozmaden_developerslife.ui.PostViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

/** ViewModel для рандомных постов */
internal class RandomPostViewModel : PostViewModel() {

    /**
     * Функция загрузки рандомного поста. Основное отличие от [CategoryPostViewModel] состоит в том,
     * что здесь добавляется только 1 пост и не учитывается страница.
     */
    override fun loadNewPost() {
        try {
            postRepository
                .getRandomPost()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { post ->
                        position++
                        _post.postValue(post)
                        _description.postValue(post.description)
                        postHistory.add(post)
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
}
