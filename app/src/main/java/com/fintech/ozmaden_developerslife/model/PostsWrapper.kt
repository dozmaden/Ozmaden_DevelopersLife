package com.fintech.ozmaden_developerslife.model

data class PostsWrapper(
    val result: List<Post>,
    val totalCount: Int
)