package suntrix.kmp.moviesapp.android.ui.components.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import suntrix.kmp.moviesapp.shared.MovieRepository
import suntrix.kmp.moviesapp.shared.injectMovieRepository
import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.logging.injectLogger

class MovieListViewModel(
    private val repository: MovieRepository = injectMovieRepository(),
    private val logger: Logger = injectLogger()
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    init {
        logger.setup("suntrix.kmp.moviesapp.android.ui.components.movies", "MovieListViewModel")
    }

    fun syncData() {
        logger.debug("syncData")

        _movies.value = listOf(
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
            Movie(
                title = "Iron Man 2",
                releaseYear = 2010,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title = "Thor",
                releaseYear = 2011,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title = "Captain America: The First Avenger",
                releaseYear = 2011,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNzAxMjg0NjYtNjNlOS00NTdlLThkMGEtMjAwYjk3NmNkOGFhXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title = "The Avengers",
                releaseYear = 2012,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
            )
        )
    }
}

data class Movie(
    val title: String,
    val releaseYear: Int,
    val imageUrl: String
)
