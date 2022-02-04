package com.fintech.ozmaden_developerslife.ui.top

import androidx.lifecycle.viewModelScope
import com.fintech.ozmaden_developerslife.ui.PostViewModel
import kotlinx.coroutines.launch

class TopPostViewModel : PostViewModel() {
    private var page: Int = 0

    override suspend fun loadNewPost() {
        val category = "top"
        viewModelScope.launch {
            val newPosts = postRepository.getCategoryPosts(category, page)
            newPosts?.let {
                position++
                page++
                postHistory.addAll(newPosts)
                _post.postValue(postHistory.elementAt(position))
            }
        }
    }
}