package com.example.movieapp.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.common.Constants
import com.example.movieapp.domain.models.MovieResponse
import com.example.movieapp.domain.models.MoviesResponse
import com.google.accompanist.coil.rememberCoilPainter
import okhttp3.internal.wait

@Composable
fun MovieItem(
    moviesItem: MoviesResponse.Result,
    onItemClick:(MoviesResponse.Result)->Unit={}
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = { onItemClick(moviesItem) }),

        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier
                    .height(100.dp)
                    .width(70.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f)
            ) {
                val image = rememberCoilPainter(
                    request = Constants.IMG_URL+moviesItem.poster_path,
                    fadeIn = true)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(70.dp)
                        ,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp,
                    )
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = moviesItem.title,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 17.sp),
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(0.dp,250.dp)
                    )
                    Row(horizontalArrangement = Arrangement.Center) {
                        Image(painterResource(
                            id =  R.drawable.ic_yellow_star),
                            contentDescription ="star image" )
                        Text(
                            text = moviesItem.vote_average.toString(),
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Gray,
                            style = typography.body2,

                            modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp)
                        )
                    }
                    
                }

                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = moviesItem.release_date.removeRange(4,moviesItem.release_date.length),
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                    Text(
                        text = moviesItem.overview,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.body2,
                        maxLines = 2
                    )
                }

            }
        }
    }

}