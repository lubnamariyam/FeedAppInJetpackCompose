package com.lubnamariyam.zapp.model

data class FeedDataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val likes : Int = 0,
)