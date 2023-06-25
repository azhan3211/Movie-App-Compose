package com.mizani.movieapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mizani.movieapp.data.model.dto.ReviewDto
import com.mizani.movieapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReviewItemComponent(reviewDto: ReviewDto) {

    val avatar = reviewDto.avatar.removePrefix("/")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = avatar,
                    contentDescription = "User Avatar Review",
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(CircleShape),
                ) {
                    it.load(avatar)
                        .error(R.drawable.ic_no_photo)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = reviewDto.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "${reviewDto.rating}")
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Review rating",
                    tint = Color.Yellow
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = reviewDto.review)
        }
    }
    
}