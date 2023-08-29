package com.sevdetneng.watchify.screens.detailscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MovieDetail(navController: NavController, id : Int,
                detailViewModel: DetailViewModel = hiltViewModel()){

    detailViewModel.getMovieById(id)
    if(detailViewModel.movie.value.id!=null){
        Text(detailViewModel.movie.value.title!!)
    }

}