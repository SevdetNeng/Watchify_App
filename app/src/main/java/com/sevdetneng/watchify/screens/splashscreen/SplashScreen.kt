package com.sevdetneng.watchify.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val auth = Firebase.auth
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1f,
            animationSpec = tween(1000,
                easing = {
                    OvershootInterpolator(5f)
                        .getInterpolation(it)
                })
        )
        delay(2000)
        if(auth.currentUser?.uid!=null){
            navController.navigate(Screens.HomeScreen.name)
        }else{
            navController.navigate(Screens.LoginScreen.name)
        }

    }

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Surface(shape = CircleShape,
                border = BorderStroke(2.dp, Color.Green.copy(0.8f)),
                color = Color.Black,
                modifier = Modifier
                    .size(330.dp)
                    .scale(scale.value)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text("Watchify", style = MaterialTheme.typography.displaySmall,
                        color = Color.Green.copy(0.7f))
                }
            }
        }
    }


}