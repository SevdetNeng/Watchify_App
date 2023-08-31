package com.sevdetneng.watchify.screens.searchscreen

import androidx.lifecycle.ViewModel
import com.sevdetneng.watchify.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(repository: ApiRepository) : ViewModel() {
}