package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Composable
fun Search(
    results: List<SearchViewModel.SearchResult>,
    onSearch: (String) -> Unit,
    onClearClick: () -> Unit,
    onCancelClick: () -> Unit,
//    modifier: Modifier = Modifier,
    expanded: Boolean = results.isNotEmpty()
) {
    var isExpanded by remember { mutableStateOf(expanded) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedContent(
            targetState = isExpanded,
            label = ""
        ) { targetState ->
            if (targetState) {
                Surface {
                    Column(
                        modifier = if (results.isNotEmpty()) {
                            Modifier.fillMaxSize()
                        } else {
                            Modifier
                        },
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SearchBar(
                            onSearch = onSearch,
                            onClearClick = onClearClick,
                            onCancelClick = {
                                isExpanded = false
                                onCancelClick()
                            }
                        )

                        if (results.isNotEmpty()) {
                            SearchResults(
                                results = results
                            )
                        }
                    }
                }
            } else {
                SearchButton {
                    isExpanded = true
                }
            }
        }
    }
}

@Composable
fun SearchResults(
    results: List<SearchViewModel.SearchResult>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(results) { index, item ->
            SearchResultsItem(
                result = item,
                modifier = if (index % 2 == 0) {
                    Modifier
                } else {
                    Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchResultsItem(
    result: SearchViewModel.SearchResult,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .then(modifier)
    ) {
        GlideImage(
            model = result.imageUrl,
            contentDescription = "${result.title} poster",
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
                text = result.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = result.releaseYear.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private class SearchPreviewProvider : PreviewParameterProvider<SearchPreviewProvider.Data> {
    data class Data(
        val results: List<SearchViewModel.SearchResult>,
        val expanded: Boolean
    )

    override val values = sequenceOf(
        Data(
            results = emptyList(),
            expanded = false
        ),
        Data(
            results = emptyList(),
            expanded = true
        ),
        Data(
            results = listOf(
                SearchViewModel.SearchResult(
                    title = "Iron Man",
                    releaseYear = "2008",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                ),
                SearchViewModel.SearchResult(
                    title = "Iron Man 2",
                    releaseYear = "2010",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                )
            ),
            expanded = true
        )
    )
}

@Preview
@Composable
private fun SearchPreview(@PreviewParameter(SearchPreviewProvider::class) data: SearchPreviewProvider.Data) {
    AppTheme {
        Search(
            results = data.results,
            onSearch = {},
            onClearClick = {},
            onCancelClick = {},
            expanded = data.expanded
        )
    }
}
