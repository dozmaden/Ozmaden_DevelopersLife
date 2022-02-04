package com.fintech.ozmaden_developerslife.ui.random

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.ui.PostViewModel
import kotlinx.coroutines.launch

class RandomPostViewModel : PostViewModel() {
    override suspend fun loadNewPost() {
        viewModelScope.launch {
            val rndPost = postRepository.getRandomPost()
            if (rndPost != null) {
                _post.postValue(rndPost!!)
                _description.postValue(rndPost.description)
                postHistory.add(rndPost)
                position++
                Log.d("RandomViewModel", "Got random post and inserted it!")
            } else {
                Log.d("RandomViewModel", "Could not insert post!")
            }
        }
    }
}