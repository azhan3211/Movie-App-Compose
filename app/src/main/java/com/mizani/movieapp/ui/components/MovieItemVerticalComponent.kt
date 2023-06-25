package com.mizani.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizani.movieapp.data.model.dto.MovieDto

@Composable
fun MovieItemVerticalComponent(movieDto: MovieDto, callback: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { callback.invoke() }
    ) {
        ImageComponent(
            imageUrl = movieDto.posterImage,
            modifier = Modifier.width(120.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = movieDto.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Release Date ${movieDto.releaseDate}",
                fontSize = 14.sp,
                maxLines = 1,
                modifier = Modifier.padding(top = 2.dp, bottom = 5.dp)
            )
            Text(
                text = movieDto.description,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}