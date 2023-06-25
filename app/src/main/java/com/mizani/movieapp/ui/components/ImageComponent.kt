package com.mizani.movieapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mizani.movieapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageComponent(
    imageUrl: String,
    modifier: Modifier = Modifier,
    rounded: Dp = 10.dp,
    contentScale: ContentScale = ContentScale.Fit,
) {

    val imageFullPath = "https://image.tmdb.org/t/p/w500$imageUrl"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(rounded)),
        shape = RoundedCornerShape(rounded)
    ) {
        GlideImage(
            model = imageFullPath,
            contentDescription = "Popular Image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = contentScale,
        ) {
            it.load(imageFullPath)
                .error(R.drawable.ic_no_photo)
        }
    }
}