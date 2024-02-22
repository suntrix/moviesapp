package suntrix.kmp.moviesapp.android.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import suntrix.kmp.moviesapp.android.ui.Movie
import suntrix.kmp.moviesapp.android.ui.MovieList
import suntrix.kmp.moviesapp.android.ui.MovieListViewModel
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Composable
fun MainScreen(
    movieListViewModel: MovieListViewModel
) {
    val movieList by movieListViewModel.movies.collectAsStateWithLifecycle()

    MainScreen(
        movieList = movieList
    )

    LaunchedEffect(key1 = movieListViewModel) {
        movieListViewModel.syncData()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    movieList: List<Movie>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchView()

            Text(
                text = "Movies",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            MovieList(
                movies = movieList
            )
        }
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit,
) {
    Button(
        shape = CircleShape,
        modifier = Modifier
            .size(54.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            onClick()
        }
    ) {
        Image(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView() {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedContent(
            targetState = expanded,
            label = ""
        ) { targetExpanded ->
            if (targetExpanded) {
                var text by rememberSaveable { mutableStateOf("") }
                var active by rememberSaveable { mutableStateOf(false) }

                SearchBar(
//                modifier = Modifier,
//                    .align(Alignment.TopCenter)
//                    .semantics { traversalIndex = -1f },
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { active = false },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = { Text("Hinted search text") },
                    leadingIcon = {
//                        SearchButton {
//
//                        }
                    Icon(Icons.Default.Search, contentDescription = null)
                    },
//                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                ) {

                }
            } else {
                Box(
                    modifier = Modifier.padding(8.dp)
                ) {
                    SearchButton {
                        expanded = true
                    }
                }
            }
        }
    }
}

//@Composable
//fun SearchBar(
////    searchText: String,
//    placeholderText: String = "",
//    onSearchTextChanged: (String) -> Unit = {},
//    onClearClick: () -> Unit = {},
//    onNavigateBack: () -> Unit = {}
//) {
//    var searchText by remember { mutableStateOf("") }
//    var showClearButton by remember { mutableStateOf(false) }
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusRequester = remember { FocusRequester() }
//
//    OutlinedTextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                color = MaterialTheme.colorScheme.primaryContainer,
//                shape = CircleShape
//            ),
//        value = searchText,
//        onValueChange = onSearchTextChanged,
//        placeholder = {
//            Text(text = placeholderText)
//        },
////        label = { Text("Search") },
////        placeholder = { Text("Search") },
//        leadingIcon = {
//            SearchButton()
////            Icon(Icons.Filled.Search, contentDescription = null)
//        },
//        shape = CircleShape,
//
//    )
//}

@Preview
@Composable
private fun MainScreenPreview() {
    val movieListViewModel = MovieListViewModel()

    AppTheme {
        MainScreen(
            movieList = listOf(
                Movie(
                    title = "Iron Man",
                    releaseYear = 2008,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                ),
                Movie(
                    title = "Captain America: The First Avenger",
                    releaseYear = 2011,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BNzAxMjg0NjYtNjNlOS00NTdlLThkMGEtMjAwYjk3NmNkOGFhXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                ),
                Movie(
                    title = "The Avengers",
                    releaseYear = 2012,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
                )
            )
        )
    }
}