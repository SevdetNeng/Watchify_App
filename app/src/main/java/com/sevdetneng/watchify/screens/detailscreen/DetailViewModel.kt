package com.sevdetneng.watchify.screens.detailscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.Backdrop
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.model.Movie
import com.sevdetneng.watchify.model.MovieImages
import com.sevdetneng.watchify.model.firebase.FbMovie
import com.sevdetneng.watchify.repository.ApiRepository
import com.sevdetneng.watchify.repository.FbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: ApiRepository,val fbRepository: FbRepository) : ViewModel() {
    val movie : MutableState<Movie> = mutableStateOf(Movie())
    val images : MutableState<MovieImages> = mutableStateOf(MovieImages())
    val isFavorite : MutableState<Boolean> = mutableStateOf(false)

    fun getMovieById(id : Int){
        viewModelScope.launch {
            try{
                when(val response = repository.getMovieById(id)){
                    is ApiResponse.Success -> {
                        movie.value = response.data!!
                        //isFavorite(id)

                    }
                    is ApiResponse.Error -> {
                        Log.d("ApiExc",response.message.toString())
                    }
                    else -> {

                    }
                }
            }catch (e : Exception){
                Log.d("ApiExc",e.message.toString())
            }
        }
    }

    fun getMovieImages(id : Int){
        viewModelScope.launch {
            try{
                when(val response = repository.getMovieImages(id)){
                    is ApiResponse.Success -> {
                        images.value = response.data!!
                        Log.d("images", images.value.backdrops.toString())

                    }
                    is ApiResponse.Error -> {
                        Log.d("ApiExc",response.message.toString())
                    }
                    else -> {

                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun addMovieToFb(movie : FbMovie){
        viewModelScope.launch {
            fbRepository.addMovie(movie)
            isFavorite(movie.id!!)
        }

    }
    fun removeMovieFromFb(id : Int){
        viewModelScope.launch {
            fbRepository.deleteMovie(id,Firebase.auth.currentUser!!.uid)
            isFavorite(id)
        }

    }

    fun isFavorite(id : Int){

        viewModelScope.launch {
            val fbMovie = FirebaseFirestore.getInstance().collection("movies").whereEqualTo("id",id)
                .whereEqualTo("user_id", Firebase.auth.currentUser?.uid).get().await()
            isFavorite.value = fbMovie.documents.isNotEmpty()
            Log.d("isFavorite",isFavorite.value.toString())
        }
    }

}