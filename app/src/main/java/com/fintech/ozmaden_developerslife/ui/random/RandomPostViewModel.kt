package com.fintech.ozmaden_developerslife.ui.random

import com.fintech.ozmaden_developerslife.ui.PostViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

internal class RandomPostViewModel : PostViewModel() {
    override fun loadNewPost() {
        postRepository.getRandomPost()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { post ->
                position++
                _post.postValue(post!!)
                _description.postValue(post.description)
                postHistory.add(post)
                _post.postValue(postHistory.elementAt(position))
            })
            .onBind()
    }
}