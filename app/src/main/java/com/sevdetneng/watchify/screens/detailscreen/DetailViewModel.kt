package com.sevdetneng.watchify.screens.detailscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.Backdrop
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.model.Movie
import com.sevdetneng.watchify.model.MovieImages
import com.sevdetneng.watchify.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: ApiRepository) : ViewModel() {
    val movie : MutableState<Movie> = mutableStateOf(Movie())
    val images : MutableState<MovieImages> = mutableStateOf(MovieImages())

    fun getMovieById(id : Int){
        viewModelScope.launch {
            try{
                when(val response = repository.getMovieById(id)){
                    is ApiResponse.Success -> {
                        movie.value = response.data!!

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

}