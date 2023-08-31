package com.sevdetneng.watchify.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailTopBar(onBackClicked: () -> Unit = {}, onFavoriteClicked: () -> Unit = {}) {

    TopAppBar(backgroundColor = Color.Transparent,
        elevation = 0.dp) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBackClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp))
            }
            IconButton(onClick = { onFavoriteClicked() }) {
                Icon(imageVector = Icons.Default.StarBorder,
                    contentDescription = "ArrowBack",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp))
            }

        }
    }

}