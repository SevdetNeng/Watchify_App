package com.sevdetneng.watchify.model

data class Backdrop(
    val aspect_ratio: Double,
    val file_path: String? = null,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)