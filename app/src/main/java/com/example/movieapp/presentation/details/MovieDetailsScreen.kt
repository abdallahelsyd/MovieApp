package com.example.movieapp.presentation.details

import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.common.Constants
import com.google.accompanist.coil.rememberCoilPainter
import okhttp3.internal.wait

@Preview
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    
    Column(
        Modifier.background(Color.Black).fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        state.movie?.let { movie ->


                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                {
                    val image = rememberCoilPainter(
                        request = Constants.IMG_URL + movie.poster_path,
                        fadeIn = true
                    )
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .height(300.dp)
                            .width(200.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop


                    )
                    Column(
                        Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Text(text = "Score:", color = Color.Gray)
                            Text(text = movie.vote_average.toString(), color = Color.White)
                        }
                        Column(Modifier.padding(0.dp, 60.dp)) {
                            Text(text = "Rating:", color = Color.Gray)
                            Text(text = movie.popularity.toString(), color = Color.White)
                        }
                        Column() {
                            Text(text = "Release Date:", color = Color.Gray)
                            Text(text = movie.release_date, color = Color.White)
                        }

                    }


                }


            Text(
                text = movie.title,
                modifier = Modifier.fillMaxWidth().padding(0.dp,30.dp).align(CenterHorizontally),
                fontSize = 30.sp,
                color = Color.White
            )
            Text(
                text = movie.overview,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
            )


        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}