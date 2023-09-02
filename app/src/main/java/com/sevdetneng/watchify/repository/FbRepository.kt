package com.sevdetneng.watchify.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.model.firebase.FbMovie
import kotlinx.coroutines.tasks.await

class FbRepository {
    suspend fun deleteMovie(id: Int,uid : String){
        val fbMovie = FirebaseFirestore.getInstance().collection("movies").whereEqualTo("id",id)
            .whereEqualTo("user_id",uid).get()
            .await().documents[0]
        FirebaseFirestore.getInstance().collection("movies").document(fbMovie.id).delete()
    }
    suspend fun addMovie(movie : FbMovie){
        FirebaseFirestore.getInstance().collection("movies")
            .add(movie).await()
    }
}