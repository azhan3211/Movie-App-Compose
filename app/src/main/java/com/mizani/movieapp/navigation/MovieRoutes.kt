package com.mizani.movieapp.navigation

sealed class MovieRoutes(val route: String) {

    object HomeScreen : MovieRoutes("home_screen")
    object DetailScreen : MovieRoutes("detail_screen")
    object FavoriteScreen : MovieRoutes("favorite_screen")

}