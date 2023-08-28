package com.sevdetneng.watchify.model

import com.google.firebase.firestore.PropertyName

data class FbUser(
    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId : String? = null,

    @get:PropertyName("display_name")
    @set:PropertyName("display_name")
    var displayName : String? = null,
    var email : String? = null,

    @get:PropertyName("photo_url")
    @set:PropertyName("photo_url")
    var photoUrl : String? = null,
    var description : String? = null,
    var country : String? = null,


){
    fun toMap() : MutableMap<String,Any?>{
        return mutableMapOf(
            "user_id" to userId,
            "display_name" to displayName,
            "email" to email,
            "description" to description,
            "country" to country,
            "photoUrl" to photoUrl
        )
    }
}
