//
//  MovieListViewModel.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 22/02/2024.
//

import Foundation

@Observable
class MovieListViewModel {
    private(set) var movies: [Movie] = []

    func syncData() {
        movies = [
            Movie(
                title: "Iron Man",
                releaseYear: 2008,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
            ),
            Movie(
                title: "The Incredible Hulk",
                releaseYear: 2008,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BMTUyNzk3MjA1OF5BMl5BanBnXkFtZTcwMTE1Njg2MQ@@._V1_SX300.jpg"
            ),
            Movie(
                title: "Iron Man 2",
                releaseYear: 2010,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title: "Thor",
                releaseYear: 2011,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title: "Captain America: The First Avenger",
                releaseYear: 2011,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BNzAxMjg0NjYtNjNlOS00NTdlLThkMGEtMjAwYjk3NmNkOGFhXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            Movie(
                title: "The Avengers",
                releaseYear: 2012,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
            )
        ]
    }
}

struct Movie: Hashable {
    let title: String
    let releaseYear: Int
    let imageUrl: String
}
