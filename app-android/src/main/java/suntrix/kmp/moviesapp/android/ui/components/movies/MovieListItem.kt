package suntrix.kmp.moviesapp.android.ui.components.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Immutable
data class Movie(
    val title: String,
    val releaseYear: Int,
    val imageUrl: String
)

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .height(160.dp),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = movie.imageUrl,
                contentDescription = "${movie.title} poster",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(2f / 3)
                    .background(Color.LightGray)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = movie.releaseYear.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieListItemPreview() {
    AppTheme {
        MovieListItem(
            movie = Movie(
                title = "Iron Man",
                releaseYear = 2008,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
            )
        )
    }
}