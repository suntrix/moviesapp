//
//  SearchViewModel.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 26/02/2024.
//

import MoviesAppShared

@Observable
class SearchViewModel {
    private let repository: MovieRepository = MovieRepositoryKt.injectMovieRepository()

    private(set) var results: [SearchResult] = []

    func clear() {
        results = []
    }

    func search(query: String) {
        repository.search(query: query) { [weak self] results, error in
            self?.results = results?.map {
                SearchResult(
                    title: $0.title,
                    releaseYear: $0.releaseYear,
                    imageUrl: $0.imageUrl
                )
            } ?? []
        }
    }

    struct SearchResult: Hashable {
        let title: String
        let releaseYear: String
        let imageUrl: String
    }
}
