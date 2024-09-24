package suntrix.kmp.moviesapp.android.ui.components.movies

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Immutable
data class MovieCollection(
    val groups: List<MovieGroup> = emptyList()
)

@Composable
fun MovieList(
    movies: MovieCollection,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        movieListItemGroups(movies.groups)
    }
}

@Preview
@Composable
private fun MovieListPreview() {
    AppTheme {
        MovieList(
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
    }
}

fun LazyListScope.movieListItemGroups(
    groups: List<MovieGroup>,
) {
    groups.forEach { movieGroup ->
        item {
            MovieListItemGroup(
                movieGroup = movieGroup,
                modifier = Modifier
                    .animateItem(
                        fadeInSpec = spring(stiffness = Spring.StiffnessMediumLow),
                        placementSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        ),
                        fadeOutSpec = spring(stiffness = Spring.StiffnessMediumLow)
                    )
            )
        }

        items(items = movieGroup.movies, key = { it.title }) { movie ->
            MovieListItem(
                movie = movie,
                modifier = Modifier
                    .animateItem(
                        fadeInSpec = spring(stiffness = Spring.StiffnessMediumLow),
                        placementSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        ),
                        fadeOutSpec = spring(stiffness = Spring.StiffnessMediumLow)
                    )
            )
        }
    }
}
