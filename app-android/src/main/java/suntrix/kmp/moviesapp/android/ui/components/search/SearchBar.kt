package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Stable
class SearchBarState {
    var searchQuery by mutableStateOf<String?>(null)
        internal set

    var placeholderText: String = "Search..."
}

@Composable
fun rememberSearchBarState() = remember { SearchBarState() }

sealed interface SearchBarAction {
    data class OnSearch(val searchQuery: String?): SearchBarAction
    data object OnClear: SearchBarAction
    data object OnCancel: SearchBarAction
}

@Composable
fun SearchBar(
    action: (SearchBarAction) -> Unit,
    modifier: Modifier = Modifier,
    state: SearchBarState = rememberSearchBarState(),
) {
    val showClearButton by remember {
        derivedStateOf {
            state.searchQuery?.isNotBlank() == true
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = state.searchQuery ?: "",
            onValueChange = { state.searchQuery = it },
            placeholder = { Text(state.placeholderText) },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .then(modifier),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                if (showClearButton) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            state.searchQuery = ""
                            action(SearchBarAction.OnClear)
                        },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    action(SearchBarAction.OnSearch(state.searchQuery))
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                }
            ),
            shape = CircleShape,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )

        Text(
            text = "Cancel",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable(
                    onClick = {
                        action(SearchBarAction.OnCancel)
                    }
                )
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

private class SearchBarPreviewProvider : PreviewParameterProvider<SearchBarState> {
    override val values = sequenceOf(
        SearchBarState().apply {
            placeholderText = "What you're looking for?"
        },
        SearchBarState().apply {
            searchQuery = "Lorem ipsum"
        }
    )
}

@Preview
@Composable
private fun SearchBarPreview(@PreviewParameter(SearchBarPreviewProvider::class) state: SearchBarState) {
    AppTheme {
        SearchBar(
            action = {},
            state = state
        )
    }
}
