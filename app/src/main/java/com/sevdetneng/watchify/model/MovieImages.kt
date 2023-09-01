package com.sevdetneng.watchify.model

data class MovieImages(
    val backdrops: List<Backdrop>? = null,
    val id: Int? = null,
    val logos: List<Logo>? = null,
    val posters: List<Poster>? = null
)