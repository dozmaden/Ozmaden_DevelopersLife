package com.fintech.ozmaden_developerslife.model

data class CategoryPosts(
    val result: Collection<Post>,
    val totalCount: Int
)