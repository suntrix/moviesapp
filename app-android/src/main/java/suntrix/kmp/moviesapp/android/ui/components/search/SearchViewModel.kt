package suntrix.kmp.moviesapp.android.ui.components.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import suntrix.kmp.moviesapp.shared.MovieRepository
import suntrix.kmp.moviesapp.shared.injectMovieRepository
import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.logging.injectLogger

class SearchViewModel(
    private val repository: MovieRepository = injectMovieRepository(),
    private val logger: Logger = injectLogger()
) : ViewModel() {

    private val _results = MutableStateFlow<List<SearchState.SearchResult>>(emptyList())
    val results: StateFlow<List<SearchState.SearchResult>>
        get() = _results

    fun clear() {
        logger.debug("SearchViewModel::clear")

        _results.value = emptyList()
    }

    fun search(query: String) {
        logger.debug("SearchViewModel::search", mapOf("query" to query))

        viewModelScope.launch {
            _results.value = repository.search(query).map {
                it.run {
                    SearchState.SearchResult(
                        title = title,
                        releaseYear = releaseYear,
                        imageUrl = imageUrl
                    )
                }
            }
        }
    }
}
