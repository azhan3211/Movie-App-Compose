package com.mizani.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizani.movieapp.data.model.dto.MovieDto

@Composable
fun MovieItemComponent(movieDto: MovieDto, modifier: Modifier = Modifier, callback: () -> Unit = {}) {
    Column(
        modifier = modifier
            .width(120.dp)
            .clickable { callback.invoke() }
    ) {
        ImageComponent(
            imageUrl = movieDto.posterImage,
            modifier = Modifier.width(120.dp).defaultMinSize(minWidth = 120.dp, minHeight = 180.dp)
        )
        Text(
            text = movieDto.title,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Release date ${movieDto.releaseDate}",
            fontSize = 14.sp
        )
    }
}