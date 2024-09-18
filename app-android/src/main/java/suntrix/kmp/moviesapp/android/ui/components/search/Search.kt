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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
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
import suntrix.kmp.moviesapp.android.ui.components.search.SearchState.SearchResult
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Stable
class SearchState {
    var searchResults by mutableStateOf<List<SearchResult>>(emptyList())

    var isExpanded by mutableStateOf(false)
        internal set

    @Immutable
    data class SearchResult(
        val title: String,
        val releaseYear: String,
        val imageUrl: String
    )
}

@Composable
fun rememberSearchState() = remember { SearchState() }

sealed interface SearchAction {
    data class OnSearch(val searchQuery: String?): SearchAction
    data object OnClear: SearchAction
    data object OnCancel: SearchAction
}

@Composable
fun Search(
    action: (SearchAction) -> Unit,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState()
) {
    val searchResultsNotEmpty by remember {
        derivedStateOf {
            state.searchResults.isNotEmpty()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedContent(
            targetState = state.isExpanded,
            label = ""
        ) { targetState ->
            if (targetState) {
                Surface {
                    Column(
                        modifier = Modifier.apply {
                            if (searchResultsNotEmpty) {
                                fillMaxSize()
                            }
                        },
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SearchBar(
                            action = {
                                when (it) {
                                    SearchBarAction.OnCancel -> {
                                        state.isExpanded = false
                                        action(SearchAction.OnCancel)
                                    }
                                    SearchBarAction.OnClear -> {
                                        action(SearchAction.OnClear)
                                    }
                                    is SearchBarAction.OnSearch -> {
                                        action(SearchAction.OnSearch(it.searchQuery))
                                    }
                                }
                            }
                        )

                        if (searchResultsNotEmpty) {
                            SearchResults(
                                results = state.searchResults
                            )
                        }
                    }
                }
            } else {
                SearchButton(
                    onClick = { state.isExpanded = true }
                )
            }
        }
    }
}

@Composable
fun SearchResults(
    results: List<SearchResult>,
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
    result: SearchResult,
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

private class SearchPreviewProvider : PreviewParameterProvider<SearchState> {
    override val values = sequenceOf(
        SearchState(),
        SearchState().apply {
            isExpanded = true
        },
        SearchState().apply {
            searchResults = listOf(
                SearchResult(
                    title = "Iron Man",
                    releaseYear = "2008",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
                ),
                SearchResult(
                    title = "Iron Man 2",
                    releaseYear = "2010",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
                )
            )
            isExpanded = true
        }
    )
}

@Preview
@Composable
private fun SearchPreview(@PreviewParameter(SearchPreviewProvider::class) state: SearchState) {
    AppTheme {
        Search(
            action = {},
            state = state
        )
    }
}
