package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {
    private val _results = MutableStateFlow<List<SearchResult>>(emptyList())

    val results: StateFlow<List<SearchResult>>
        get() = _results

    fun clear() {
        _results.value = emptyList()
    }

    fun search(query: String) {
        _results.value = listOf(
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
        )
    }
}

data class SearchResult(
    val title: String,
    val releaseYear: Int,
    val imageUrl: String
)
