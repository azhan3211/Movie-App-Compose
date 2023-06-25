package com.mizani.movieapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
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
import com.mizani.movieapp.ui.components.MovieItemComponent
import com.mizani.movieapp.R
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.navigation.MovieRoutes
import com.mizani.movieapp.ui.components.MovieItemPopularComponent
import com.mizani.movieapp.ui.screen.SharedViewModel
import com.mizani.movieapp.ui.screen.ViewState

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val moviePopular = viewModel.moviePopular.value
    val movieTopRated = viewModel.movieTopRated.value
    val movieNowPlaying = viewModel.movieNowPlaying.value

    if (viewModel.isPopularLoaded.not()) {
        LaunchedEffect(key1 = true) {
            viewModel.getMoviePopular()
        }
    }

    if (viewModel.isNowPlayingLoaded.not()) {
        LaunchedEffect(key1 = true) {
            viewModel.getMovieNowPlaying()
        }
    }

    if (viewModel.isTopRatedLoaded.not()) {
        LaunchedEffect(key1 = true) {
            viewModel.getMovieTopRated()
        }
    }

    Scaffold(
        topBar = { Appbar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            MovieList(
                label = stringResource(id = R.string.popular),
                moviePopular,
                isPopular = true
            ) { movieDto ->
                sharedViewModel.setMovieDetail(movieDto)
                navController.navigate(MovieRoutes.DetailScreen.route)
            }
            Spacer(modifier = Modifier.height(30.dp))
            MovieList(
                label = stringResource(id = R.string.top_rated),
                movieTopRated
            ) { movieDto ->
                sharedViewModel.setMovieDetail(movieDto)
                navController.navigate(MovieRoutes.DetailScreen.route)
            }
            Spacer(modifier = Modifier.height(30.dp))
            MovieList(
                label = stringResource(id = R.string.now_playing),
                movieNowPlaying
            ) { movieDto ->
                sharedViewModel.setMovieDetail(movieDto)
                navController.navigate(MovieRoutes.DetailScreen.route)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}

@Composable
private fun Appbar(navController: NavController) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Home Favorite entry point",
                tint = Color.White,
                modifier = Modifier.clickable {
                    navController.navigate(MovieRoutes.FavoriteScreen.route)
                }
            )
        }
    }
}

@Composable
private fun MovieList(label: String, state: ViewState<List<MovieDto>, Exception>, isPopular: Boolean = false, callback: (MovieDto) -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        when (state) {
            is ViewState.Loading -> {
                CircularProgressIndicator()
            }
            is ViewState.Success -> {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                ) {
                    items(state.data.size) {
                        if (it == 0) {
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        if (isPopular) {
                            MovieItemPopularComponent(
                                movieDto = state.data[it],
                                modifier = Modifier.clickable { callback.invoke(state.data[it]) }
                            )
                        } else {
                            MovieItemComponent(state.data[it]) { callback.invoke(state.data[it]) }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            is ViewState.Error -> {
                Text(text = state.error.message.toString())
            }
        }
    }
}
