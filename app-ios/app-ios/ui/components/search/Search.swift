//
//  SearchView.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 23/02/2024.
//

import SwiftUI

struct Search: View {
    var results: [SearchResult] = []
    let onSearch: (String) -> Void
    let onClearClick: () -> Void
    let onCancelClick: () -> Void

    @State var isExpanded = false

    var body: some View {
        HStack {
            if isExpanded {
                ZStack {
                    VStack {
                        SearchBar(
                            onSearch: onSearch,
                            onClearClick: onClearClick,
                            onCancelClick: {
                                isExpanded = false
                                onCancelClick()
                            }
                        )

                        if !results.isEmpty {
                            SearchResults(results: results)
                        }
                    }
                }
            } else {
                Spacer()

                SearchButton {
                    withAnimation {
                        isExpanded = true
                    }
                }
            }
        }
    }
}

struct SearchResults: View {
    var results: [SearchResult]

    var body: some View {
        ScrollView {
            LazyVStack(
                spacing: 4
            ) {
                ForEach(results, id: \.self) { result in
                    SearchResultsItem(result: result)
                }
            }
        }
    }
}

struct SearchResultsItem: View {
    var result: SearchResult

    var body: some View {
        HStack(
            alignment: .top
        ) {
            AsyncImage(
                url: URL(string: result.imageUrl),
                content: { image in
                    image.resizable()
                        .aspectRatio(2/3, contentMode: .fit)
                },
                placeholder: {}
            )

            VStack(
                alignment: .leading
            ) {
                Text(result.title)
                    .font(.body)

                Text(String(result.releaseYear))
                    .font(.body)
            }
            .padding(8)

            Spacer()
        }
        .frame(height: 80)
    }
}

#Preview {
    VStack(
        alignment: .trailing
    ) {
        Search(
            results: [],
            onSearch: { _ in },
            onClearClick: {},
            onCancelClick: {},
            isExpanded: false
        )

        Search(
            results: [], 
            onSearch: { _ in },
            onClearClick: {},
            onCancelClick: {},
            isExpanded: true
        )

        Search(
            results: [
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
            ],
            onSearch: { _ in },
            onClearClick: {}, 
            onCancelClick: {},
            isExpanded: true
        )
    }
}
