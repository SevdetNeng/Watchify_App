package com.sevdetneng.watchify.model

data class ListResponse(
    val page: Int? = null,
    val results: List<ListMovie>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)