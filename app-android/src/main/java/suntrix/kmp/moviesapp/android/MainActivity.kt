package suntrix.kmp.moviesapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import suntrix.kmp.moviesapp.android.ui.MovieListViewModel
import suntrix.kmp.moviesapp.android.ui.screens.MainScreen
import suntrix.kmp.moviesapp.android.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val movieListViewModel by viewModels<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen(
                    movieListViewModel = movieListViewModel
                )
            }
        }
    }
}
