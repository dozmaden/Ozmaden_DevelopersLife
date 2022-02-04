package com.fintech.ozmaden_developerslife.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.repository.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
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
                Log.w("HomeViewModel", e.message.toString())
            }
        }
    }

    private suspend fun loadNewPost() {
        viewModelScope.launch {
            val rndPost = postRepository.getRandomPost()
            if (rndPost != null) {
                _post.postValue(rndPost!!)
                Log.d("HomeViewModel", "Got random post and inserted it!")
            } else {
                Log.d("HomeViewModel", "Could not insert post!")
            }
        }
    }
}