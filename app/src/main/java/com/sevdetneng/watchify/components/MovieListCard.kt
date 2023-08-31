package com.sevdetneng.watchify.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import com.sevdetneng.watchify.model.ListMovie

@Composable
fun MovieListCard(movie: ListMovie, onCardClick: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .height(275.dp)
            .width(150.dp)
            .clickable {
                onCardClick(movie.id!!)
            }
            //.padding(horizontal = 8.dp)
        ,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            val imageLoader = LocalContext.current.imageLoader.newBuilder()
                .logger(DebugLogger())
                .build()
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.poster_path}",
                contentDescription = "",
                modifier = Modifier
                    .height(225.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .padding(bottom = 4.dp),
                contentScale = ContentScale.FillBounds,
                imageLoader = imageLoader
            )
            Text(
                movie.title!!, style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                movie.release_date!!.trim().split('-')[0], style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }


}