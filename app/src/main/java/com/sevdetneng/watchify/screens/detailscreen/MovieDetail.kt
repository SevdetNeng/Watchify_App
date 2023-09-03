package com.sevdetneng.watchify.screens.detailscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.components.DetailCarousel
import com.sevdetneng.watchify.components.DetailTopBar
import com.sevdetneng.watchify.components.PopularMovieCarousel
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.model.Movie
import com.sevdetneng.watchify.model.firebase.FbMovie
import com.sevdetneng.watchify.utils.Constants
import com.sevdetneng.watchify.utils.Constants.IMAGE_BASE_URL

@Composable
fun MovieDetail(
    navController: NavController, id: Int,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    detailViewModel.getMovieById(id)
    detailViewModel.getMovieImages(id)
    detailViewModel.isFavorite(id)
    val movie = detailViewModel.movie.value
    val isFavorite = detailViewModel.isFavorite.value
    val images = detailViewModel.images.value

    Scaffold(
        topBar = {
            DetailTopBar(onBackClicked = {
                navController.popBackStack()
            }, onFavoriteClicked = {
                if (!isFavorite) {
                    detailViewModel.addMovieToFb(
                        FbMovie(
                            title = movie.title!!,
                            id = id,
                            posterPath = movie.poster_path,
                            productionDate = movie.release_date,
                            rating = movie.vote_average,
                            userId = Firebase.auth.currentUser?.uid
                        )
                    )
                } else {
                    detailViewModel.removeMovieFromFb(id)
                }
            },
                isFavorite = isFavorite
            )
        },
        bottomBar = { WatchifyBottomBar(navController = navController) },
        containerColor = Color(0xff111820)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = padding.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {

            if (movie.id != null) {
                Column(modifier = Modifier.fillMaxSize()) {
                    if (movie.backdrop_path != null) {
                        MoviePoster(path = movie.backdrop_path!!, movie)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            movie.overview!!, style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (images.backdrops != null) {
                            DetailCarousel(
                                backdrops = if (images.backdrops.size <= 10) images.backdrops
                                else images.backdrops.take(10)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (movie.belongs_to_collection != null) {
                            CollectionRow(movie = movie)

                        }

                        if (movie.production_companies!!.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(), horizontalArrangement = Arrangement.Center
                            ) {
                                movie.production_companies!!.forEachIndexed() { index, company ->
                                    if (index == movie.production_companies.size - 1) {
                                        Text(
                                            company.name + ", ${company.origin_country}",
                                            color = Color.Gray,
                                            style = MaterialTheme.typography.labelMedium,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    } else {
                                        Text(
                                            company.name + ", ${company.origin_country} - ",
                                            color = Color.Gray,
                                            style = MaterialTheme.typography.labelMedium,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                    }

                }
            }
        }
    }


}

@Composable
fun MoviePoster(path: String, movie: Movie) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    val brush = Brush.verticalGradient(listOf(Color.Transparent, Color(0xff111820)))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        shape = RectangleShape
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = Constants.IMAGE_BASE_URL + path, contentDescription = "Poster",
                contentScale = ContentScale.FillBounds,
                imageLoader = imageLoader
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                    //.background(brush)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            movie.title.toString(),
                            color = Color.White,
                            modifier = Modifier.width(200.dp),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            movie.release_date!!.trim(),
                            color = Color.Gray,
                            modifier = Modifier.width(200.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Row(
                            modifier = Modifier.width(200.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (genre in movie.genres!!) {
                                Text(
                                    genre.name.trim() + " ",
                                    color = Color.Gray,
                                    //modifier = Modifier.width(200.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "TMDB ${"%.1f".format(movie.vote_average)}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Icon(
                                    imageVector = Icons.Outlined.StarBorder,
                                    contentDescription = "Score",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "YOU 3.5",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Icon(
                                    imageVector = Icons.Outlined.StarBorder,
                                    contentDescription = "Score",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CollectionRow(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            movie.belongs_to_collection!!.name,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(125.dp),
                shape = RectangleShape
            ) {
                AsyncImage(
                    model = IMAGE_BASE_URL + movie.belongs_to_collection.poster_path,
                    contentDescription = "Backdrop",
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                AsyncImage(
                    model = IMAGE_BASE_URL + movie.belongs_to_collection.backdrop_path,
                    contentDescription = "Backdrop",
                    contentScale = ContentScale.FillBounds
                )
            }

        }
    }
}