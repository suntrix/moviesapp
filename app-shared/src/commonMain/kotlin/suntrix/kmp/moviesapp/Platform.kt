package suntrix.kmp.moviesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform