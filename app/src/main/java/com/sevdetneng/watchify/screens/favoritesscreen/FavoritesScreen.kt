package com.sevdetneng.watchify.screens.favoritesscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.watchify.components.MovieListCard
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.navigation.Screens

@Composable
fun FavoritesScreen(navController: NavController,favoritesViewModel: FavoritesViewModel = hiltViewModel()){


    favoritesViewModel.getAllFavorites()
    val favorites = favoritesViewModel.favoriteMovies.value
    Scaffold(bottomBar = { WatchifyBottomBar(navController = navController,screen = "Favorites")},
        containerColor = Color(0xff111820)
    ) { padding->
        Column(modifier = Modifier.fillMaxSize()
            .padding(bottom = padding.calculateBottomPadding())
            .padding(start = 16.dp,top = 16.dp,end = 16.dp)){
            Text("Favorites", style = MaterialTheme.typography.titleLarge,
                color = Color.White)
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)){
                items(favorites){ movie ->
                    MovieListCard(movie = movie, onCardClick = {
                        navController.navigate(Screens.DetailScreen.name+"/$it")
                    })
                }
            }
        }
    }
}