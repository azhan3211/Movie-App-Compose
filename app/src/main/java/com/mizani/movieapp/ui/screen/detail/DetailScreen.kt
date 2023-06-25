package com.mizani.movieapp.ui.screen.detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mizani.movieapp.R
import com.mizani.movieapp.data.model.dto.MovieDto
import com.mizani.movieapp.data.model.dto.ReviewDto
import com.mizani.movieapp.ui.components.ImageComponent
import com.mizani.movieapp.ui.components.ReviewItemComponent
import com.mizani.movieapp.ui.screen.SharedViewModel
import com.mizani.movieapp.ui.screen.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val data = sharedViewModel.movieDetail.value
    val reviewState = viewModel.reviewState.value
    val isFavorite = viewModel.isFavorite.value
    val coroutineScope = rememberCoroutineScope()
    val isFavoriteButtonEnabled = rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    if (viewModel.isLoaded.not()) {
        LaunchedEffect(key1 = true) {
            viewModel.isFavorite(data.id)
            viewModel.getReview(data.id)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        bottomBar = {
            ActionSection(isFavorite, isFavoriteButtonEnabled) {
                when (it) {
                    is ActionDetail.Share -> {
                        share(context, data)
                    }
                    is ActionDetail.Favorite -> {
                        coroutineScope.launch {
                            isFavoriteButtonEnabled.value = false
                            if (isFavorite.not()) {
                                viewModel.addFavorite(data)
                            } else {
                                viewModel.removeFavorite(data.id)
                            }
                            delay(1000L)
                            isFavoriteButtonEnabled.value = true
                        }
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            AppbarImage(data = data) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = data.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.release_date) + " ${data.releaseDate}",
                    fontSize = 14.sp,
                )
                Text(
                    text = stringResource(id = R.string.description),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 10.dp),
                )
                Text(
                    text = data.description,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 10.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                ReviewSection(state = reviewState)
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun ReviewSection(state: ViewState<List<ReviewDto>, Exception>) {
    when (state) {
        is ViewState.Loading -> {
            CircularProgressIndicator()
        }
        is ViewState.Success -> {
            repeat(state.data.size) {
                ReviewItemComponent(reviewDto = state.data[it])
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        is ViewState.Error -> {
            Text(text = state.error.message.toString())
        }
    }
}

private fun share(context: Context, movieDto: MovieDto) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, movieDto.title)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, null)
    context.startActivity(shareIntent)
}

@Composable
private fun AppbarImage(data: MovieDto, callback: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        ImageComponent(
            imageUrl = data.thumbnailImage,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.6f / 1f)
                .shadow(0.dp),
            rounded = 0.dp,
            contentScale = ContentScale.FillBounds
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            modifier = Modifier.padding(horizontal = 16.dp),
            elevation = 0.dp
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Detail back button",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        callback.invoke()
                    },
                tint = Color.White
            )
        }
    }
}

@Composable
private fun ActionSection(
    isFavorite: Boolean,
    isFavoriteEnabled: State<Boolean>,
    callback: (ActionDetail) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (isFavorite) {
                Icons.Outlined.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = "Favorite Icon",
            tint = if (isFavorite) {
                Color.Red
            } else {
                Color.Black
            },
            modifier = Modifier.clickable {
                if (isFavoriteEnabled.value.not()) return@clickable
                callback.invoke(ActionDetail.Favorite)
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            Icons.Outlined.Share,
            contentDescription = "Favorite Icon",
            modifier = Modifier.clickable {
                callback.invoke(ActionDetail.Share)
            },
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}
