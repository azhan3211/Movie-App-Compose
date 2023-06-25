package com.mizani.movieapp.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mizani.movieapp.R
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.navigation.MovieRoutes
import com.mizani.movieapp.ui.components.MovieItemVerticalComponent
import com.mizani.movieapp.ui.screen.SharedViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val movies = viewModel.movies.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.getMovieFavoriteAll()
    }

    Scaffold(
        topBar = {
          Appbar {
              navController.popBackStack()
          }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        MovieListSection(movies = movies) {
            sharedViewModel.setMovieDetail(movies[it])
            navController.navigate(MovieRoutes.DetailScreen.route)
        }
    }
}

@Composable
private fun Appbar(callback: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Favorite back icon",
                tint = Color.White,
                modifier = Modifier.clickable { callback.invoke() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.favorite_movie),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun MovieListSection(movies: List<MovieDto>, callback: (Int) -> Unit) {

    if (movies.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.favorite_no_list),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(movies.size) {
                if (it == 0) {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                MovieItemVerticalComponent(movies[it]) {
                    callback.invoke(it)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}