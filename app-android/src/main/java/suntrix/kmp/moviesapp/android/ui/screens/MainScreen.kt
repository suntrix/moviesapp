package suntrix.kmp.moviesapp.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import suntrix.kmp.moviesapp.android.ui.components.movies.Movie
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieCollection
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieGroup
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieList
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieListViewModel
import suntrix.kmp.moviesapp.android.ui.components.search.Search
import suntrix.kmp.moviesapp.android.ui.components.search.SearchAction
import suntrix.kmp.moviesapp.android.ui.components.search.SearchState
import suntrix.kmp.moviesapp.android.ui.components.search.SearchViewModel
import suntrix.kmp.moviesapp.android.ui.components.search.rememberSearchState
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Composable
fun MainScreen(
    searchViewModel: SearchViewModel,
    movieListViewModel: MovieListViewModel
) {
    val searchResults by searchViewModel.results.collectAsStateWithLifecycle()
    val movies by movieListViewModel.groups.collectAsStateWithLifecycle()

    val movieCollection by remember {
        derivedStateOf {
            MovieCollection(
                groups = movies
            )
        }
    }

    val searchState = rememberSearchState()

    MainScreen(
        searchState = searchState,
        searchAction = {
            when (it) {
                SearchAction.OnCancel,
                SearchAction.OnClear -> searchViewModel.clear()

                is SearchAction.OnSearch -> it.searchQuery?.run { searchViewModel.search(this) }
            }
        },
        movies = movieCollection
    )

    LaunchedEffect(searchResults) {
        searchState.searchResults = searchResults
    }

    LaunchedEffect(movieListViewModel) {
        movieListViewModel.syncData()
    }
}

@Composable
fun MainScreen(
    searchAction: (SearchAction) -> Unit,
    movies: MovieCollection,
    searchState: SearchState = rememberSearchState()
) {
    val showList by remember {
        derivedStateOf {
            searchState.searchResults.isEmpty()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Search(
                state = searchState,
                action = searchAction
            )

            if (showList) {
                Text(
                    text = "Movies",
                    style = MaterialTheme.typography.headlineMedium
                )

                MovieList(
                    movies = movies
                )
            }
        }
    }
}

private class MainScreenPreviewProvider : PreviewParameterProvider<MainScreenPreviewProvider.Data> {
    data class Data(
        val searchState: SearchState,
        val movies: MovieCollection
    )

    override val values = sequenceOf(
        Data(
            searchState = SearchState().apply {
                searchResults = emptyList()
            },
            movies = MovieCollection()
        ),
        Data(
            searchState = SearchState().apply {
                searchResults = emptyList()
            },
            movies = MovieCollection(
                groups = listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = listOf(
                            Movie(
                                title = "Iron Man",
                                releaseYear = 2008,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                            ),
                            Movie(
                                title = "The Incredible Hulk",
                                releaseYear = 2008,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTUyNzk3MjA1OF5BMl5BanBnXkFtZTcwMTE1Njg2MQ@@._V1_SX300.jpg"
                            ),
                        )
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = listOf(
                            Movie(
                                title = "Iron Man 3",
                                releaseYear = 2013,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjIzMzAzMjQyM15BMl5BanBnXkFtZTcwNzM2NjcyOQ@@._V1_SX300.jpg"
                            ),
                            Movie(
                                title = "Thor: The Dark World",
                                releaseYear = 2013,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTQyNzAwOTUxOF5BMl5BanBnXkFtZTcwMTE0OTc5OQ@@._V1_SX300.jpg"
                            ),
                        )
                    )
                )
            )
        ),
        Data(
            searchState = SearchState().apply {
                searchResults = listOf(
                    SearchState.SearchResult(
                        title = "Iron Man",
                        releaseYear = "2008",
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                    ),
                    SearchState.SearchResult(
                        title = "Iron Man 2",
                        releaseYear = "2010",
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                    )
                )
            },
            movies = MovieCollection()
        ),
        Data(
            searchState = SearchState().apply {
                searchResults = listOf(
                    SearchState.SearchResult(
                        title = "Iron Man",
                        releaseYear = "2008",
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                    ),
                    SearchState.SearchResult(
                        title = "Iron Man 2",
                        releaseYear = "2010",
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                    )
                )
            },
            movies = MovieCollection(
                groups = listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = listOf(
                            Movie(
                                title = "Iron Man",
                                releaseYear = 2008,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                            ),
                            Movie(
                                title = "The Incredible Hulk",
                                releaseYear = 2008,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTUyNzk3MjA1OF5BMl5BanBnXkFtZTcwMTE1Njg2MQ@@._V1_SX300.jpg"
                            ),
                        )
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = listOf(
                            Movie(
                                title = "Iron Man 3",
                                releaseYear = 2013,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjIzMzAzMjQyM15BMl5BanBnXkFtZTcwNzM2NjcyOQ@@._V1_SX300.jpg"
                            ),
                            Movie(
                                title = "Thor: The Dark World",
                                releaseYear = 2013,
                                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTQyNzAwOTUxOF5BMl5BanBnXkFtZTcwMTE0OTc5OQ@@._V1_SX300.jpg"
                            ),
                        )
                    )
                )
            )
        )
    )
}

@Preview
@Composable
private fun MainScreenPreview(@PreviewParameter(MainScreenPreviewProvider::class) data: MainScreenPreviewProvider.Data) {
    AppTheme {
        MainScreen(
            searchState = data.searchState,
            searchAction = {},
            movies = data.movies
        )
    }
}