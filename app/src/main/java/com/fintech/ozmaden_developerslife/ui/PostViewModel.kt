package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.repository.PostRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch

internal abstract class PostViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected val postRepository = PostRepository()

    protected val _post = MutableLiveData<Post>()
    internal val post: LiveData<Post> = _post

    protected val _description = MutableLiveData<String>()
    internal val description: LiveData<String> = _description

    protected val postHistory = mutableListOf<Post>()
    internal var position: Int = -1

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

//    init {
//        loadPost()
//    }

    internal fun nextPost() {
        loadPost()
    }

    private fun loadPost() {
        viewModelScope.launch {
            try {
                if (postHistory.size > position + 1) {
                    loadCachedPost()
                } else {
                    loadNewPost()
                }
            } catch (e: Exception) {
                Log.w("PostViewModel", e.message.toString())
            }
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