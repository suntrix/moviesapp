package suntrix.kmp.moviesapp.android.ui.components.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Immutable
data class MovieGroup(
    val name: String,
    val movies: List<Movie>
)

@Composable
fun MovieListItemGroup(
    movieGroup: MovieGroup,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = movieGroup.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun MovieListItemGroupPreview() {
    AppTheme {
        MovieListItemGroup(
            movieGroup = MovieGroup(
                name = "MCU Phase 1",
                movies = emptyList()
            )
        )
    }
}