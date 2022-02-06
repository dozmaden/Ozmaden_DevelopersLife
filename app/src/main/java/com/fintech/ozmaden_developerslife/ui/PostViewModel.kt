package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.repository.PostRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

internal abstract class PostViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected val _post = MutableLiveData<Post>()
    internal val post: LiveData<Post> = _post

    protected val _description = MutableLiveData<String>()
    internal val description: LiveData<String> = _description

    protected val loadingFail = MutableLiveData<Boolean>()
    internal val onLoadFail: LiveData<Boolean> = loadingFail

    protected val postRepository = PostRepository()

    protected val postHistory = mutableListOf<Post>()
    internal var position: Int = -1

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    internal fun nextPost() {
        loadPost()
    }

    private fun loadPost() {
        try {
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

    protected abstract fun loadNewPost()

    internal fun previousPost() {
        if (position > 0) {
            position--
            val oldPost = postHistory.elementAt(position)
            _post.postValue(oldPost)
        }
    }

    protected fun Disposable.onBind() = disposables.add(this)
}
