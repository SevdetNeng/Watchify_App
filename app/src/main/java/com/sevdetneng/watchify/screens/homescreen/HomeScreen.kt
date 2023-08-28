package com.sevdetneng.watchify.screens.homescreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController,homeViewModel: HomeViewModel = hiltViewModel()){
    Text(homeViewModel.nowPlayingResult.value.results.toString())
}