package com.mizani.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mizani.movieapp.ui.screen.detail.DetailScreen
import com.mizani.movieapp.ui.screen.favorite.FavoriteScreen
import com.mizani.movieapp.ui.screen.SharedViewModel
import com.mizani.movieapp.ui.screen.home.HomeScreen

@Composable
fun MovieNavigation(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieRoutes.HomeScreen.route) {
        composable(route = MovieRoutes.HomeScreen.route) {
            HomeScreen(navController, sharedViewModel)
        }
        composable(
            route = MovieRoutes.DetailScreen.route
        ) {
            DetailScreen(navController, sharedViewModel)
        }
        composable(route = MovieRoutes.FavoriteScreen.route) {
            FavoriteScreen(navController, sharedViewModel)
        }
    }
}