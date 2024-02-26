//
//  MainScreen.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 23/02/2024.
//

import SwiftUI

struct MainScreen: View {
    @State private var searchViewModel = SearchViewModel()
    @State private var movieListViewModel = MovieListViewModel()

    var body: some View {
        let searchResults = searchViewModel.results

        ZStack(
            alignment: .top
        ) {
            VStack(
                alignment: .leading,
                spacing: 16
            ) {
                Search(
                    results: searchResults,
                    onSearch: { query in searchViewModel.search(query: query) },
                    onClearClick: { searchViewModel.clear() },
                    onCancelClick: { searchViewModel.clear() }
                )

                if searchResults.isEmpty {
                    Text("Movies")
                        .font(.largeTitle)

                    MovieList(
                        movies: movieListViewModel.movies
                    )
                }
            }
            .padding(8)
            .onAppear(perform: {
                movieListViewModel.syncData()
            })
        }
    }
}

#Preview {
    MainScreen()
}
