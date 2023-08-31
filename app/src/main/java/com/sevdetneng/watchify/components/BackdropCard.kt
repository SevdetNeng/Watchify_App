package com.sevdetneng.watchify.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.utils.Constants.IMAGE_BASE_URL

@Composable
fun BackdropCard(modifier: Modifier = Modifier, movie : ListMovie, onCardClick : (Int) -> Unit = {}){
    Column(){
        Card(modifier = modifier
            .height(180.dp)
            .width(350.dp)
            .clickable {
                       onCardClick(movie.id!!)
            },
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black)

            ){
            Box(modifier = Modifier.fillMaxSize()){
                val imageLoader = LocalContext.current.imageLoader.newBuilder()
                    .logger(DebugLogger())
                    .build()



                AsyncImage(model = IMAGE_BASE_URL+movie.backdrop_path, contentDescription = "Backdrop",
                    imageLoader = imageLoader,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize())
                Row(verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxSize()){
                    val brush = Brush.verticalGradient(listOf(Color.Black.copy(0.05f),Color.Black))
                    Box(modifier = Modifier
                        .background(brush)
                        .height(25.dp)
                        .fillMaxWidth(),
                        ){
                        Row(modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(movie.title!!, style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp,
                                color = Color.Gray)
                            Spacer(Modifier.width(12.dp))
                            Text(movie.release_date.toString().split('-')[0], style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp,
                                color = Color.Gray)
                        }

                    }

                }
            }

        }
    }

}