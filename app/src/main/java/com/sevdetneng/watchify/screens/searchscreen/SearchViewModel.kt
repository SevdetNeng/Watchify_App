package com.sevdetneng.watchify.screens.searchscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: ApiRepository) : ViewModel() {

    val searchResults : MutableState<ListResponse> = mutableStateOf(ListResponse())
    val isLoading = mutableStateOf(true)
    fun searchMovie(searchQuery : String){
        viewModelScope.launch {
            try{
                when(val response = repository.searchMovie(searchQuery)){
                    is ApiResponse.Success -> {
                        searchResults.value = response.data!!
                        isLoading.value = false
                    }
                    is ApiResponse.Error -> {
                        Log.d("Exc",response.message.toString())
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