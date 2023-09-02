package com.sevdetneng.watchify.screens.searchscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.model.ListMovie
import com.sevdetneng.watchify.utils.Constants.IMAGE_BASE_URL
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import com.sevdetneng.watchify.navigation.Screens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel = hiltViewModel()) {
    val searchValueState: MutableState<String> = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val results = searchViewModel.searchResults.value
    val isValid = remember(searchValueState.value) {
        searchValueState.value.trim().isNotEmpty()
    }
    Scaffold(
        bottomBar = {
            WatchifyBottomBar(
                navController = navController,
                screen = "Search"
            )
        },
        containerColor = Color(0xff111820)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            SearchBar(value = searchValueState, label = "Search", onAction = KeyboardActions {
                if (isValid) {
                    //searchViewModel.searchMovie(searchValueState.value)
                }
                keyboardController!!.hide()

            }) {
                searchValueState.value = it
                searchViewModel.searchMovie(searchValueState.value)

            }

            if (results.results != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(results.results!!) {
                        SearchMovieRow(movie = it) {
                            navController.navigate(Screens.DetailScreen.name + "/$it")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun SearchBar(
    value: MutableState<String>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
    onValueChange: (String) -> Unit = {}
) {
    val focusState = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value.value, onValueChange = {
            onValueChange(it)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                focusState.value = it.isFocused
            },
        keyboardActions = onAction,
        leadingIcon = {
            Icon(
                imageVector = if (focusState.value) Icons.Outlined.ArrowBackIos else Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )


    )
}

@Composable
fun SearchMovieRow(movie: ListMovie, onCardClick: (Int) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .clickable {
                onCardClick(movie.id!!)
            },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xff293038))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                AsyncImage(model = IMAGE_BASE_URL + movie.poster_path, contentDescription = "img")
                Column(modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, end = 8.dp)) {
                    Text(
                        movie.title!!,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        movie.release_date!!.trim().split('-')[0],
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "TMDB : ${"%.1f".format(movie.vote_average)}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                            Icon(
                                imageVector = Icons.Outlined.StarBorder, "",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                        }

                    }
                }
            }
        }
    }
}