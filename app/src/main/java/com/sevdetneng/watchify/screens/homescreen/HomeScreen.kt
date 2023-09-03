package com.sevdetneng.watchify.screens.homescreen


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.components.ListTitle
import com.sevdetneng.watchify.components.MovieListCard
import com.sevdetneng.watchify.components.PopularMovieCarousel
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.navigation.Screens


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    homeViewModel.getFavoriteMovies()

    Scaffold(bottomBar = {
        WatchifyBottomBar(
            navController = navController,
            screen = "Home"
        )
    }, containerColor = Color(0xff111820)
        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically){
                val uid = Firebase.auth.currentUser?.uid
                var displayName  = remember{ mutableStateOf("") }
                val userName = FirebaseFirestore.getInstance().collection("users")
                    .whereEqualTo("user_id",uid).get()
                    .addOnSuccessListener {

                        displayName.value = it.documents[0].data!!.getValue("display_name").toString()
                        Log.d("firebasesuccess",displayName.value)
                    }
                Text("Welcome, ${displayName.value}",
                    color = Color.White,
                    modifier = Modifier.padding(end = 4.dp))
            }

            if (homeViewModel.isTrendingLoading.value) {
                LinearProgressIndicator()
            } else {
                PopularMovieCarousel(
                    movies = homeViewModel.nowPlayingResult.value.results,
                    navController = navController
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                ListTitle(title = "Trending Movies"){
                    navController.navigate(Screens.MoviesScreen.name+"/trending")
                }
                if(!homeViewModel.isTrendingLoading.value){
                    MovieLazyRow(movies = homeViewModel.trendingResult.value.results!!, navController = navController)
                }


                ListTitle(title = "Top-Rated Movies"){
                    navController.navigate(Screens.MoviesScreen.name+"/toprated")
                }
                if(!homeViewModel.isTopRatedLoading.value){
                    MovieLazyRow(movies = homeViewModel.topRatedResult.value.results!!, navController = navController)
                }

                ListTitle(title = "Favorites"){
                    navController.navigate(Screens.FavoritesScreen.name)
                }
                if(!homeViewModel.isTrendingLoading.value){
                    MovieLazyRow(movies = homeViewModel.favoriteMovies.value, navController = navController)
                }


            }

        }

    }

}

@Composable
fun MovieLazyRow(movies : List<ListMovie>, navController: NavController){
    LazyRow(modifier = Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)


    ){
        items(movies){ movie->
            MovieListCard(movie = movie, onCardClick = {
                navController.navigate(Screens.DetailScreen.name+"/$it")
            })
        }
    }
}





