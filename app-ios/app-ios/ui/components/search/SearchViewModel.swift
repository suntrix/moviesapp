//
//  SearchViewModel.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 26/02/2024.
//

import Foundation

@Observable
class SearchViewModel {
    private(set) var results: [SearchResult] = []

    func clear() {
        results = []
    }

    func search(query: String) {
        results = [
            SearchResult(
                title: "Iron Man",
                releaseYear: 2008,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"
            ),
            SearchResult(
                title: "Iron Man 2",
                releaseYear: 2010,
                imageUrl: "https://m.media-amazon.com/images/M/MV5BZGVkNDAyM2EtYzYxYy00ZWUxLTgwMjgtY2VmODE5OTk3N2M5XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            )
        ]
    }
}

struct SearchResult: Hashable {
    let title: String
    let releaseYear: Int
    let imageUrl: String
}
