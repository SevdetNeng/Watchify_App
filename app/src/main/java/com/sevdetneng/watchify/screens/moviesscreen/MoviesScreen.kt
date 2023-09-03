package com.sevdetneng.watchify.screens.moviesscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.watchify.components.MovieListCard
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.navigation.Screens
import com.sevdetneng.watchify.screens.searchscreen.SearchMovieRow

@Composable
fun MoviesScreen(navController: NavController, listName : String,
                 moviesViewModel: MoviesViewModel = hiltViewModel()){
    val gridState = rememberLazyGridState()
    val movies = moviesViewModel.movies
    LaunchedEffect(gridState.canScrollForward){
        if(!gridState.canScrollForward){
            moviesViewModel.getNextPage()
        }
    }
    Log.d("listname",listName)
    if(moviesViewModel.listType.value!=listName){
        moviesViewModel.reset()
        moviesViewModel.listType.value = listName
    }

    Scaffold(bottomBar = {
        WatchifyBottomBar(navController = navController)
    },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color(0xff111820)
    ) { padding->
        Column(modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()){
            Text(text = if(listName == "trending") "Trending"
                else if(listName == "toprated") "Top-Rated"
                else "Now Playing" , style = MaterialTheme.typography.titleLarge,
                color = Color.White)
            Spacer(Modifier.height(16.dp))
            if(movies.value.results!=null){
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    state = gridState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)){
                    itemsIndexed(movies.value.results!!){ index,item ->
                        MovieListCard(movie = item, onCardClick = {
                            navController.navigate(Screens.DetailScreen.name+"/${item.id}")
                        })
                    }
                    item {
                        if(moviesViewModel.isLoading.value){
                            Column(modifier = Modifier.fillMaxSize()){
                                LinearProgressIndicator(backgroundColor = Color.White)
                            }
                        }
                    }
                }
            }

        }
    }






}