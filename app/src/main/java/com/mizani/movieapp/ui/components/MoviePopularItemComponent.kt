package com.mizani.movieapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mizani.movieapp.data.model.dto.MovieDto

@Composable
fun MovieItemPopularComponent(
    movieDto: MovieDto,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(300.dp)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        ImageComponent(
            imageUrl = movieDto.thumbnailImage,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(10.dp))
                .aspectRatio(ratio = 2f / 1f),
            contentScale = ContentScale.FillBounds
        )
        Surface(
            color = Color.Black.copy(0.6f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(50.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieDto.title,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}