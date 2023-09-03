package com.sevdetneng.watchify.repository

import androidx.compose.runtime.MutableState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.model.firebase.FbMovie
import com.sevdetneng.watchify.model.firebase.FbUser
import kotlinx.coroutines.flow.Flow
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

    suspend fun getAllFavorites(uid : String) : List<ListMovie>{
        val documents = FirebaseFirestore.getInstance().collection("movies").whereEqualTo("user_id",uid).get().await().documents
        val movies = documents.map {
            it.toObject(FbMovie::class.java)!!
        }
        val listMovies : MutableList<ListMovie> = emptyList<ListMovie>().toMutableList()
        for(movie in movies){
            listMovies.add(
                ListMovie(
                    id = movie?.id,
                    poster_path = movie?.posterPath,
                    title = movie?.title,
                    release_date = movie?.productionDate,
                    vote_average = movie?.rating,
                )
            )
        }
        return listMovies
    }

    suspend fun getUserData(uid : String) : FbUser{
        val documents = FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("user_id",uid).get().await().documents
        val user = documents.map {
            it.toObject(FbUser::class.java)!!
        }
        return if(user[0].userId!=null){
            user[0]
        }else{
            FbUser()
        }
    }

    fun updateUserData(newData : FbUser){
        val documents = FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("user_id",newData.userId).get().addOnCompleteListener{
                if(it.isSuccessful){
                    FirebaseFirestore.getInstance().collection("users").document(it.result.documents[0].id).update(newData.toMap())
                }
            }
    }
}