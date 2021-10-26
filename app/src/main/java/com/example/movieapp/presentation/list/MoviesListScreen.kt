package com.example.movieapp.presentation.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.domain.models.MoviesResponse
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.list.components.MovieItem

@Composable
fun MoviesListScreen(
    navController: NavController,
    viewModel: MoviesListViewModel= hiltViewModel(),
    context:Context
){
    val state=viewModel.state.value
    val listState = rememberLazyListState()

    val moviesListItems: LazyPagingItems<MoviesResponse.Result> = viewModel.movie.collectAsLazyPagingItems()


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(moviesListItems) { item ->
                item?.let { movie ->
                    MovieItem(movie, onItemClick = {
                        Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                        //navController.navigate(Screen.MovieDetailScreen.route + "/${movie.id}")
                    })
                }
            }
            moviesListItems.apply {
                when {
                    loadState.refresh is LoadState.Loading ->
                        item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }
                    loadState.append is LoadState.Loading ->
                        item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }

                    loadState.append is LoadState.Error ->
                        item { Text("Error", color = Color.Red, fontSize = 30.sp) }
                    loadState.refresh is LoadState.Error -> item {
                        Text(
                            "Error",
                            color = Color.Red,
                            fontSize = 30.sp
                        )
                    }
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}