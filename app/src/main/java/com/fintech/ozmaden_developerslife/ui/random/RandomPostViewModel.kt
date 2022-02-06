package com.fintech.ozmaden_developerslife.ui.random

import com.fintech.ozmaden_developerslife.repository.PostRepository
import com.fintech.ozmaden_developerslife.ui.PostViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

internal class RandomPostViewModel : PostViewModel() {

    override fun loadNewPost() {
        PostRepository.getRandomPost()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { post ->
                    position++
                    _post.postValue(post)
                    postHistory.add(post)
                    _post.postValue(postHistory.elementAt(position))
                },
                onError = { loadingFail.postValue(true) }
            )
            .onBind()
    }
}
