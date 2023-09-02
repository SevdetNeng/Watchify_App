package com.sevdetneng.watchify.screens.moviesscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val repository: ApiRepository) : ViewModel() {

    val movies : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val page = mutableIntStateOf(1)
    val maxPageCount = mutableIntStateOf(2)
    val listType = mutableStateOf("")
    val isLoading = mutableStateOf(true)
    val isEnd : MutableState<Boolean> = mutableStateOf(true)
    val pageSize = 20

    fun getNextPage(){
        if(page.value<=maxPageCount.value){
            if(listType.value == "toprated") getTopRatedMovies(page.value)
            if(listType.value == "nowplaying") getNowPlayingMovies(page.value)
            if(listType.value == "trending") getTrendingMovies(page.value)
        }
        else {
            isLoading.value = false
        }
    }

    fun reset(){
        movies.value = ListResponse()
        page.value = 1
        maxPageCount.value = 1
        listType.value = ""
        isLoading.value = true
        isEnd.value = true
    }

    fun getTrendingMovies(currentPage : Int){

        viewModelScope.launch {
            try{
                isLoading.value = true
                when(val response = repository.getTrendingMovies(currentPage)){
                    is ApiResponse.Success -> {
                        Log.d("success",response.data?.results.toString())
                        if(currentPage==1){
                            movies.value.results = response.data!!.results
                        }else{
                            movies.value = movies.value.copy(results = movies.value.results!! + response.data!!.results!!)
                        }

                        if(movies.value.results!=null){
                            isLoading.value = false
                        }
                        page.intValue++
                        maxPageCount.intValue = response.data.total_pages!!-1
                        isEnd.value = false
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
    fun getNowPlayingMovies(currentPage : Int){

        viewModelScope.launch {
            try{
                isLoading.value = true
                when(val response = repository.getNowPlayingMovies(currentPage)){
                    is ApiResponse.Success -> {
                        Log.d("success",response.data?.results.toString())
                        if(currentPage==1){
                            movies.value.results = response.data!!.results
                        }else{
                            movies.value = movies.value.copy(results = movies.value.results!! + response.data!!.results!!)
                        }

                        if(movies.value.results!=null){
                            isLoading.value = false
                        }
                        page.intValue++
                        maxPageCount.intValue = response.data.total_pages!!-1
                        isEnd.value = false
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
    fun getTopRatedMovies(currentPage : Int){
        viewModelScope.launch {
            try{
                isLoading.value = true
                when(val response = repository.getTopRatedMovies(currentPage)){
                    is ApiResponse.Success -> {
                        Log.d("success",response.data?.results.toString())
                        if(currentPage==1){
                            movies.value.results = response.data!!.results
                        }else{
                            movies.value = movies.value.copy(results = movies.value.results!! + response.data!!.results!!)
                        }

                        if(movies.value.results!=null){
                            isLoading.value = false
                        }
                        page.intValue++
                        maxPageCount.intValue = response.data.total_pages!!-1
                        isEnd.value = false
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