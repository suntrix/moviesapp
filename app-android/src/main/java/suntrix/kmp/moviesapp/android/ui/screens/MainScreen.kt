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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import suntrix.kmp.moviesapp.android.ui.components.movies.Movie
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieList
import suntrix.kmp.moviesapp.android.ui.components.movies.MovieListViewModel
import suntrix.kmp.moviesapp.android.ui.components.search.Search
import suntrix.kmp.moviesapp.android.ui.components.search.SearchResult
import suntrix.kmp.moviesapp.android.ui.components.search.SearchViewModel
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Composable
fun MainScreen(
    searchViewModel: SearchViewModel,
    movieListViewModel: MovieListViewModel
) {
    val searchResults by searchViewModel.results.collectAsStateWithLifecycle()
    val movieList by movieListViewModel.movies.collectAsStateWithLifecycle()

    MainScreen(
        searchResults = searchResults,
        onSearch = { searchViewModel.search(it) },
        onSearchCleared = { searchViewModel.clear() },
        onSearchCanceled = { searchViewModel.clear() },
        movieList = movieList
    )

    LaunchedEffect(key1 = movieListViewModel) {
        movieListViewModel.syncData()
    }
}

@Composable
fun MainScreen(
    searchResults: List<SearchResult>,
    onSearch: (String) -> Unit,
    onSearchCleared: () -> Unit,
    onSearchCanceled: () -> Unit,
    movieList: List<Movie>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Search(
                results = searchResults,
                onSearch = onSearch,
                onClearClick = onSearchCleared,
                onCancelClick = onSearchCanceled
            )

            if (searchResults.isEmpty()) {
                Text(
                    text = "Movies",
                    style = MaterialTheme.typography.headlineMedium
                )

                MovieList(
                    movies = movieList
                )
            }
        }
    }
}

private class MainScreenPreviewProvider : PreviewParameterProvider<MainScreenPreviewProvider.Data> {
    data class Data(
        val searchResults: List<SearchResult>,
        val movieList: List<Movie>
    )

    override val values = sequenceOf(
        Data(
            searchResults = emptyList(),
            movieList = emptyList()
        ),
        Data(
            searchResults = emptyList(),
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
        ),
        Data(
            searchResults = listOf(
                SearchResult(
                    title = "Iron Man",
                    releaseYear = 2008,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                ),
                SearchResult(
                    title = "Iron Man 2",
                    releaseYear = 2010,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                )
            ),
            movieList = emptyList()
        ),
        Data(
            searchResults = listOf(
                SearchResult(
                    title = "Iron Man",
                    releaseYear = 2008,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                ),
                SearchResult(
                    title = "Iron Man 2",
                    releaseYear = 2010,
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                )
            ),
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
    )
}

@Preview
@Composable
private fun MainScreenPreview(@PreviewParameter(MainScreenPreviewProvider::class) data: MainScreenPreviewProvider.Data) {
    AppTheme {
        MainScreen(
            searchResults = data.searchResults,
            onSearch = {},
            onSearchCleared = {},
            onSearchCanceled = {},
            movieList = data.movieList
        )
    }
}