package com.sevdetneng.watchify.model.firebase

import com.google.firebase.firestore.PropertyName

data class FbMovie(
    var id : Int? = null,

    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId : String? = null,

    var title : String? = null,

    @get:PropertyName("poster_path")
    @set:PropertyName("poster_path")
    var posterPath : String? = null,

    @get:PropertyName("release_date")
    @set:PropertyName("release_date")
    var productionDate : String? = null,

    var rating : Double? = null
){
    fun toMap() : MutableMap<String,Any?>{
        return mutableMapOf(
            "id" to id,
            "user_id" to userId,
            "title" to title,
            "poster_path" to posterPath,
            "release_date" to productionDate,
            "rating" to rating
        )
    }
}