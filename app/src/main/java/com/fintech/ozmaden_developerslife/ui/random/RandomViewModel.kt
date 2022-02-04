package com.fintech.ozmaden_developerslife.ui.random

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.repository.PostRepository
import kotlinx.coroutines.launch

class RandomViewModel : ViewModel() {

    private val postRepository = PostRepository()

    private val _post = MutableLiveData<Post>()

    val post: LiveData<Post> = _post
    private val _description = MutableLiveData<String>()

    val description: LiveData<String> = _description

    private val postHistory = mutableListOf<Post>()

    internal var position: Int = -1

    init {
        loadPost()
    }

    internal fun onNext() {
        loadPost()
    }

    internal fun onPrevious() {
        if (position > 0) {
            position--
            val oldPost = postHistory.elementAt(position)
            _post.postValue(oldPost)
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
        position++
        val oldPost = postHistory.elementAt(position)
        _post.postValue(oldPost)
    }

    private suspend fun loadNewPost() {
        viewModelScope.launch {
            val rndPost = postRepository.getRandomPost()
            if (rndPost != null) {
                _post.postValue(rndPost!!)
                _description.postValue(rndPost.description)
                Log.d("RandomViewModel", "Got random post and inserted it!")
            } else {
                Log.d("RandomViewModel", "Could not insert post!")
            }
        }
    }
}