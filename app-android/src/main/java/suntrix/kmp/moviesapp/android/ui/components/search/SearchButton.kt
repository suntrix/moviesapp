package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

@Composable
fun SearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        shape = CircleShape,
        modifier = Modifier
            .size(50.dp)
            .then(modifier),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
        )
    }
}

@Preview
@Composable
private fun SearchButtonPreview() {
    AppTheme {
        SearchButton(
            onClick = {}
        )
    }
}