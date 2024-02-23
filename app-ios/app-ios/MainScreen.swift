//
//  MainScreen.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 23/02/2024.
//

import SwiftUI

struct MainScreen: View {
    @State private var movieListViewModel = MovieListViewModel()

    var body: some View {
        VStack(
            alignment: .leading,
            spacing: 16
        ) {
            SearchView()

            Text("Movies")
                .font(.largeTitle)

            MovieList(movies: movieListViewModel.movies)
        }
        .padding(8)
        .onAppear(perform: {
            movieListViewModel.syncData()
        })
    }
}

#Preview {
    MainScreen()
}


struct SearchButton: View {
    var body: some View {
        Button {
            onClick()
        } label: {
            Image(systemName: "magnifyingglass")
        }
        .buttonStyle(.borderedProminent)
        .buttonBorderShape(.circle)
        .controlSize(.large)
        .tint(Color("primary"))
    }

    private let onClick: () -> Void

    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
}

struct SearchBar: View {
    @Binding var text: String

    var body: some View {
        ZStack(
            alignment: .leading
        ) {
            RoundedRectangle(cornerRadius: 24)
                .fill(Color("surfaceVariant"))

            HStack {
                Image(systemName: "magnifyingglass")
            }
            .padding(16)
        }
        .frame(height: 50)
    }

//    @State private var isEditing = false
//
//    var body: some View {
//        HStack {
//            TextField("Search ...", text: $text)
//                .padding(7)
//                .padding(.horizontal, 25)
//                .background(Color(.systemGray6))
//                .cornerRadius(8)
//                .padding(.horizontal, 10)
//                .onTapGesture {
//                    self.isEditing = true
//                }
//
//            if isEditing {
//                Button(action: {
//                    self.isEditing = false
//                    self.text = ""
//
//                }) {
//                    Text("Cancel")
//                }
//                .padding(.trailing, 10)
//                .transition(.move(edge: .trailing))
//                .animation(.default)
//            }
//        }
//    }
}

struct SearchView: View {
    @State private var searchText = ""

    @State private var expanded = false

    var body: some View {
        HStack {
            Spacer()

            if expanded {
                SearchBar(text: $searchText)
            } else {
                SearchButton {
                    withAnimation {
                        expanded = true
                    }
                }
            }
        }
    }
}
