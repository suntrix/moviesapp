package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import suntrix.kmp.moviesapp.shared.omdb.OMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.internal.DefaultOMDbApiRepository
import suntrix.kmp.moviesapp.shared.omdb.internal.OMDbApiDataSource
import suntrix.kmp.moviesapp.shared.omdb.internal.apiclient.ApiClient

class SearchViewModel(
    private val repository: OMDbApiRepository = DefaultOMDbApiRepository()
) : ViewModel() {
    private val _results = MutableStateFlow<List<SearchResult>>(emptyList())

    val results: StateFlow<List<SearchResult>>
        get() = _results

    fun clear() {
        _results.value = emptyList()
    }

    fun search(query: String) {
        viewModelScope.launch {
            _results.value = repository.search(query).map {
                it.run { SearchResult(title, releaseYear, imageUrl) }
            }
        }
    }

    data class SearchResult(
        val title: String,
        val releaseYear: String,
        val imageUrl: String
    )
}
