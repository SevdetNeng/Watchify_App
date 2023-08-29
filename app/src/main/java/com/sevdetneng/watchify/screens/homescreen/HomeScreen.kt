package com.sevdetneng.watchify.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.watchify.components.PopularMovieCarousel
import com.sevdetneng.watchify.components.WatchifyBottomBar



@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    Scaffold(bottomBar = {
        WatchifyBottomBar(
            navController = navController,
            screen = "Home"
        )
    }) { padding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(vertical = 16.dp)) {
//            val imageLoader = LocalContext.current.imageLoader.newBuilder()
//                .logger(DebugLogger())
//                .build()
//            AsyncImage(model = "https://image.tmdb.org/t/p/w500/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg", contentDescription = "",
//                modifier = Modifier.fillMaxSize(),
//                imageLoader = imageLoader
//                    )
            if (homeViewModel.isTrendingLoading.value) {

            } else {
                PopularMovieCarousel(
                    movies = homeViewModel.trendingResult.value.results,
                    navController = navController
                )
//                MovieListCard(
//                    movie = homeViewModel.trendingResult.value.results!![6],
//                    onCardClick = {})
            }

        }

    }

}



