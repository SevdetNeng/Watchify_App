package com.sevdetneng.watchify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sevdetneng.watchify.navigation.Navigation
import com.sevdetneng.watchify.ui.theme.WatchifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Content()

        }
    }
}


@Composable
fun MainContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Navigation()
    }
}

@Composable
fun Content() {
    WatchifyTheme {
        // A surface container using the 'background' color from the theme
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material.MaterialTheme.colors.background
        ) {
            Column(
                modifier =
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Navigation()
            }

        }
    }
}

