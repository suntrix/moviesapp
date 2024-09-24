package suntrix.kmp.moviesapp.android.ui.components.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import suntrix.kmp.moviesapp.shared.MovieRepository
import suntrix.kmp.moviesapp.shared.injectMovieRepository
import suntrix.kmp.moviesapp.shared.logging.Logger
import suntrix.kmp.moviesapp.shared.logging.injectLogger
import kotlin.time.Duration.Companion.seconds

class MovieListViewModel(
    private val repository: MovieRepository = injectMovieRepository(),
    private val logger: Logger = injectLogger()
) : ViewModel() {

    private val _groups = MutableStateFlow<List<MovieGroup>>(emptyList())
    val groups: StateFlow<List<MovieGroup>>
        get() = _groups

    fun syncData() {
        logger.debug("MovieListViewModel::syncData")

        viewModelScope.launch(Dispatchers.Default) {
            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = emptyList()
                    )
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = listOf(
                            phase1.first()
                        )
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = emptyList()
                    )
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = listOf(
                            phase1.first()
                        )
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = listOf(
                            phase2.first()
                        )
                    )
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = emptyList()
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = listOf(
                            phase1.first(),
                            phase2.first()
                        )
                    )
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = phase1.subList(0, 2)
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = phase2.subList(0, 2)
                    )
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = phase2.subList(0, 2)
                    ),
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = phase1.subList(0, 2).reversed()
                    ),
                )
            )

            delay(3.seconds)

            _groups.emit(
                listOf(
                    MovieGroup(
                        name = "MCU Phase 1",
                        movies = phase1.subList(0, 2)
                    ),
                    MovieGroup(
                        name = "MCU Phase 2",
                        movies = phase2.subList(0, 2)
                    )
                )
            )
        }
    }

    companion object {
        val phase1 = mutableListOf(
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

        val phase2 = mutableListOf(
            Movie(
                title = "Iron Man 3",
                releaseYear = 2013,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjIzMzAzMjQyM15BMl5BanBnXkFtZTcwNzM2NjcyOQ@@._V1_SX300.jpg"
            ),
            Movie(
                title = "Thor: The Dark World",
                releaseYear = 2013,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTQyNzAwOTUxOF5BMl5BanBnXkFtZTcwMTE0OTc5OQ@@._V1_SX300.jpg"
            ),
            Movie(
                title = "Captain America: The Winter Soldier",
                releaseYear = 2014,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BYzMyN2I0NjMtYmZhZS00MWJkLWE1MTktNGM2ZDhmNDE1ZDc0XkEyXkFqcGdeQXVyNDg2NzE0MjE@._V1_SX300.jpg"
            ),
            Movie(
                title = "Guardians of the Galaxy",
                releaseYear = 2014,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNDIzMTk4NDYtMjg5OS00ZGI0LWJhZDYtMzdmZGY1YWU5ZGNkXkEyXkFqcGdeQXVyMTI5NzUyMTIz._V1_SX300.jpg"
            ),
            Movie(
                title = "Avengers: Age of Ultron",
                releaseYear = 2015,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTM4OGJmNWMtOTM4Ni00NTE3LTg3MDItZmQxYjc4N2JhNmUxXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title = "Ant-Man",
                releaseYear = 2015,
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjM2NTQ5Mzc2M15BMl5BanBnXkFtZTgwNTcxMDI2NTE@._V1_SX300.jpg"
            )
        )
    }
}
