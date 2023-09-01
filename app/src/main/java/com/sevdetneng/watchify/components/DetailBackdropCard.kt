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
import com.sevdetneng.watchify.model.Backdrop
import com.sevdetneng.watchify.utils.Constants

@Composable
fun DetailBackdropCard(modifier : Modifier = Modifier,backdrop: Backdrop) {

    Column(){
        Card(modifier = modifier
            .height(180.dp)
            .width(350.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black)

        ){
            Box(modifier = Modifier.fillMaxSize()){
                val imageLoader = LocalContext.current.imageLoader.newBuilder()
                    .logger(DebugLogger())
                    .build()



                AsyncImage(model = Constants.IMAGE_BASE_URL +backdrop.file_path, contentDescription = "Backdrop",
                    imageLoader = imageLoader,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize())
            }

        }
    }

}