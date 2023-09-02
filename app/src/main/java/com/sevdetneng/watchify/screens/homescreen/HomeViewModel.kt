package com.sevdetneng.watchify.screens.homescreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository : ApiRepository) : ViewModel() {
    val trendingResult : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val topRatedResult : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val nowPlayingResult : MutableState<ListResponse> = mutableStateOf(ListResponse())

    val isTrendingLoading : MutableState<Boolean> = mutableStateOf(true)
    val isTopRatedLoading : MutableState<Boolean> = mutableStateOf(true)
    val isNowPlayingLoading : MutableState<Boolean> = mutableStateOf(true)

    init {
        getTrendingMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
    }

    private fun getTrendingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                when(val response = repository.getTrendingMovies(page = 1)){
                    is ApiResponse.Success -> {
                        Log.d("Api","Success")
                        trendingResult.value = response.data!!
                        isTrendingLoading.value = false
                    }
                    is ApiResponse.Error -> {
                        Log.d("ApiExc1",response.message.toString())
                    }
                    else -> {}
                }
            }catch (e : Exception){
                Log.d("Exc",e.message.toString())
            }
        }
    }

    private fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                when(val response = repository.getTopRatedMovies(page = 1)){
                    is ApiResponse.Success -> {
                        topRatedResult.value = response.data!!
                        isTopRatedLoading.value = false
                    }
                    is ApiResponse.Error -> {
                        Log.d("ApiExc2",response.message.toString())
                    }
                    else -> {}
                }
            }catch (e : Exception){
                Log.d("Exc",e.message.toString())
            }
        }
    }
    private fun getNowPlayingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                when(val response = repository.getNowPlayingMovies(page = 1)){
                    is ApiResponse.Success -> {
                        nowPlayingResult.value = response.data!!
                        isNowPlayingLoading.value = false
                    }
                    is ApiResponse.Error -> {
                        Log.d("ApiExc3",response.message.toString())
                    }
                    else -> {}
                }
            }catch (e : Exception){
                Log.d("Exc",e.message.toString())
            }
        }
    }
}