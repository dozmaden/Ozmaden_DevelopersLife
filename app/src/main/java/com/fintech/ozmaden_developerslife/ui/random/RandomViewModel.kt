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

    private val _text = MutableLiveData<String>().apply {
        value = "This is Random Fragment"
    }

    val text: LiveData<String> = _text

    //
    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    protected val postRepository = PostRepository()

    init {
        loadPost()
    }

    private fun loadPost() {
        viewModelScope.launch {
            try {
                loadNewPost()
            } catch (e: Exception) {
                Log.w("RandomViewModel", e.message.toString())
            }
        }
    }

    private suspend fun loadNewPost() {
        viewModelScope.launch {
            val rndPost = postRepository.getRandomPost()
            if (rndPost != null) {
                _post.postValue(rndPost!!)
                Log.d("RandomViewModel", "Got random post and inserted it!")
            } else {
                Log.d("RandomViewModel", "Could not insert post!")
            }
        }
    }
}