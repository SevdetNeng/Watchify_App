package com.sevdetneng.watchify.screens.favoritesscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.repository.FbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val repository: FbRepository) : ViewModel() {
    val favoriteMovies : MutableState<List<ListMovie>> = mutableStateOf(emptyList())
    fun getAllFavorites(){
        viewModelScope.launch {
           favoriteMovies.value = repository.getAllFavorites(Firebase.auth.currentUser!!.uid)
        }
    }
}