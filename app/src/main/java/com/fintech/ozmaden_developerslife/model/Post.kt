package com.fintech.ozmaden_developerslife.model

import com.google.gson.annotations.SerializedName

data class Post (
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    @SerializedName("gifURL")
    val gifURL: String,
    val gifSize: Int,
    @SerializedName("previewURL")
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: String,
    val height: String,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)