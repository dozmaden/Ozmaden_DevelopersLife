package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fintech.ozmaden_developerslife.dto.Post
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/** Основной ViewModel для работы с постами. */
internal abstract class PostViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected val _post = MutableLiveData<Post>()
    internal val post: LiveData<Post> = _post

    protected val loadingFail = MutableLiveData<Boolean>()
    internal val onLoadFail: LiveData<Boolean> = loadingFail

    protected val postHistory = mutableListOf<Post>()
    internal var position: Int = -1

    protected abstract fun loadNewPost()

    protected fun Disposable.onBind() = disposables.add(this)

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    internal fun loadPrevPost() {
        if (position > 0) {
            position--
            _post.postValue(postHistory.elementAt(position))
        }
    }

    internal fun loadNextPost() {
        try {
            // если пост уже загружался, то просто двигаемся
            if (postHistory.size - position > 1) {
                loadCachedPost()
            } else {
                loadNewPost()
            }
            loadingFail.postValue(false)
        } catch (e: Exception) {
            Log.w("PostViewModel", e.message.toString())
            loadingFail.postValue(true)
        }
    }

    private fun loadCachedPost() {
        position++
        val oldPost = postHistory.elementAt(position)
        _post.postValue(oldPost)
    }
}
