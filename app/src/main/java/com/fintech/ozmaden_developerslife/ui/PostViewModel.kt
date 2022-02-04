package com.fintech.ozmaden_developerslife.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.repository.PostRepository
import kotlinx.coroutines.launch

abstract class PostViewModel : ViewModel() {
    protected val postRepository = PostRepository()

    protected val _post = MutableLiveData<Post>()
    internal val post: LiveData<Post> = _post

    protected val _description = MutableLiveData<String>()
    internal val description: LiveData<String> = _description

    protected val postHistory = mutableListOf<Post>()
    internal var position: Int = -1

    init {
        nextPost()
    }

    internal fun nextPost() {
        loadPost()
    }

    internal fun previousPost() {
        if (position > 0) {
            val oldPost = postHistory.elementAt(position)
            _post.postValue(oldPost)
            position--
        }
    }

    private fun loadPost() {
        viewModelScope.launch {
            try {
                if (postHistory.size > position + 1) {
                    loadOldPost()
                } else {
                    loadNewPost()
                }
            } catch (e: Exception) {
                Log.w("PageViewModel", e.message.toString())
            }
        }
    }

    private fun loadOldPost() {
        val oldPost = postHistory.elementAt(position)
        _post.postValue(oldPost)
        position++
    }

    protected abstract suspend fun loadNewPost()
}