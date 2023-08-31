package com.sevdetneng.watchify.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sevdetneng.watchify.navigation.Screens

@Composable
fun WatchifyBottomBar(navController: NavController,
                      screen : String? = null
                      ){

    BottomAppBar(modifier = Modifier.height(50.dp)) {
        Row(modifier = Modifier.fillMaxSize()
            .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){

            Column(){
                IconButton(onClick = { navController.navigate(Screens.HomeScreen.name) }) {
                    if(screen=="Home"){
                        Icon(imageVector = Icons.Outlined.Home,"Home",
                            modifier = Modifier.size(35.dp),
                            tint = Color(0xff47a3d0))
                    }else{
                        Icon(imageVector = Icons.Outlined.Home,"Home",
                            modifier = Modifier.size(35.dp),
                            tint = Color.DarkGray)
                    }

                }
                Text("Home")
            }


            Column(){
                IconButton(onClick = { navController.navigate(Screens.SearchScreen.name) }) {
                    if(screen=="Search"){
                        Icon(imageVector = Icons.Outlined.Search,"Search",
                            modifier = Modifier.size(35.dp),
                            tint = Color(0xff47a3d0))
                    }else{
                        Icon(imageVector = Icons.Outlined.Search,"Search",
                            modifier = Modifier.size(35.dp),
                            tint = Color.DarkGray)
                    }

                }
                Text("Search")
            }
            Column(){
                IconButton(onClick = { /*TODO*/ }) {
                    if(screen=="Favorites"){
                        Icon(imageVector = Icons.Outlined.List,"List",
                            modifier = Modifier.size(35.dp),
                            tint = Color(0xff47a3d0))
                    }else{
                        Icon(imageVector = Icons.Outlined.List,"List",
                            modifier = Modifier.size(35.dp),
                            tint = Color.DarkGray)
                    }

                }
                Text("My List")
            }
            Column(){
                IconButton(onClick = { /*TODO*/ }) {
                    if(screen=="User"){
                        Icon(imageVector = Icons.Outlined.Person,"Person",
                            modifier = Modifier.size(35.dp),
                            tint = Color(0xff47a3d0))
                    }else{
                        Icon(imageVector = Icons.Outlined.Person,"Person",
                            modifier = Modifier.size(35.dp),
                            tint = Color.DarkGray)
                    }

                }
                Text("Person")
            }
        }
    }
}