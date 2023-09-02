package com.sevdetneng.watchify.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sevdetneng.watchify.screens.detailscreen.DetailViewModel
import com.sevdetneng.watchify.screens.detailscreen.MovieDetail
import com.sevdetneng.watchify.screens.homescreen.HomeScreen
import com.sevdetneng.watchify.screens.homescreen.HomeViewModel
import com.sevdetneng.watchify.screens.loginscreen.LoginScreen
import com.sevdetneng.watchify.screens.moviesscreen.MoviesScreen
import com.sevdetneng.watchify.screens.moviesscreen.MoviesViewModel
import com.sevdetneng.watchify.screens.searchscreen.SearchScreen
import com.sevdetneng.watchify.screens.searchscreen.SearchViewModel
import com.sevdetneng.watchify.screens.splashscreen.SplashScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val homeViewModel : HomeViewModel = hiltViewModel()
    val detailViewModel : DetailViewModel = hiltViewModel()
    val searchViewModel : SearchViewModel = hiltViewModel()
    val moviesViewModel : MoviesViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(Screens.HomeScreen.name){
            HomeScreen(navController = navController,homeViewModel = homeViewModel)
        }
        composable(Screens.SearchScreen.name){
            SearchScreen(navController = navController,searchViewModel)
        }


        composable(Screens.DetailScreen.name + "/{id}", arguments =
            listOf(navArgument("id"){
                type = NavType.IntType
            }) ){backStack->
            backStack.arguments?.getInt("id").let {
                MovieDetail(navController = navController, id = it!!,detailViewModel = detailViewModel)
            }
        }
        composable(Screens.MoviesScreen.name + "/{listName}", arguments =
        listOf(navArgument("listName"){
            type = NavType.StringType
        }) ){backStack->
            backStack.arguments?.getString("listName").let {
                MoviesScreen(navController = navController, listName = it!!,moviesViewModel)
            }
        }
    }
}