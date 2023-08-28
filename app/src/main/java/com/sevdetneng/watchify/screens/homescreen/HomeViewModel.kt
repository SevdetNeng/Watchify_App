package com.sevdetneng.watchify.screens.homescreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.network.TmdbApi
import com.sevdetneng.watchify.utils.Constants.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val tmdbApi: TmdbApi) : ViewModel() {
    val trendingResult : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val topRatedResult : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val nowPlayingResult : MutableState<ListResponse> = mutableStateOf(ListResponse())
    init {
        getTrendingMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
    }

    private fun getTrendingMovies(){
        viewModelScope.launch {
            try{
                trendingResult.value = tmdbApi.getTrendingMovies(API_KEY)
            }catch (e : Exception){
                Log.d("ApiExc",e.message.toString())
            }

        }
    }
    private fun getTopRatedMovies(){
        viewModelScope.launch {
            try{
                topRatedResult.value = tmdbApi.getTopRatedMovies(API_KEY)
            }catch (e : Exception){
                Log.d("ApiExc",e.message.toString())
            }

        }
    }
    private fun getNowPlayingMovies(){
        viewModelScope.launch {
            try{
                nowPlayingResult.value = tmdbApi.getNowPlayingMovies(API_KEY)
            }catch (e : Exception){
                Log.d("ApiExc",e.message.toString())
            }

        }
    }
}