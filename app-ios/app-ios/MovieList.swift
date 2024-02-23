//
//  MovieList.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 22/02/2024.
//

import SwiftUI

struct MovieList: View {
    var body: some View {
        ScrollView {
            LazyVStack(
                spacing: 8
            ) {
                ForEach(movies, id: \.self) { movie in
                    MovieListItem(movie: movie)
                }
            }
        }
    }

    private let movies: [Movie]

    init(movies: [Movie]) {
        self.movies = movies
    }
}

struct MovieListItem: View {
    var body: some View {
        ZStack(
            alignment: .leading
        ) {
            RoundedRectangle(cornerRadius: 8)
                .fill(Color("surfaceVariant"))

            HStack(
                alignment: .top
            ) {
                AsyncImage(
                    url: URL(string: movie.imageUrl),
                    content: { image in
                        image.resizable()
                            .aspectRatio(2/3, contentMode: .fit)
                            .background()
                    },
                    placeholder: {
                    }
                )
                .clipShape(
                    UnevenRoundedRectangle(
                        cornerRadii: .init(
                            topLeading: 8,
                            bottomLeading: 8
                        )
                    )
                )

                VStack(
                    alignment: .leading
                ) {
                    Text(movie.title)
                        .font(.body)

                    Text(String(movie.releaseYear))
                        .font(.body)
                }
                .padding(8)
            }
        }
        .frame(height: 160)
//        .padding(8)
    }

    private let movie: Movie

    init(movie: Movie) {
        self.movie = movie
    }
}

#Preview {
    MovieList(
        movies: [
            Movie(
                title: "Iron Man",
                releaseYear: 2008,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
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
    )
}
