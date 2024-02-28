//
//  SearchBar.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 26/02/2024.
//

import SwiftUI

struct SearchBar: View {
    let onSearch: (String) -> Void
    let onClearClick: () -> Void
    let onCancelClick: () -> Void

    @State var searchQuery: String = ""
    var placeholderText: String = "Search..."

//    private var showClearButton: Binding<Bool> {
//        Binding {
//            return !self.searchQuery.isEmpty
//        } set: { _ in }
//    }

    @FocusState private var isEditing: Bool

    var body: some View {
        HStack(
            alignment: .center,
            spacing: 4
        ) {
            ZStack{
                RoundedRectangle(cornerRadius: 24)
                    .fill(Color("surfaceVariant"))

                HStack {
                    Image(systemName: "magnifyingglass")
                        .foregroundColor(Color("primary"))

                    TextField(placeholderText, text: $searchQuery)
                        .lineLimit(1)
                        .focused($isEditing)
                        .keyboardType(.default)
                        .onSubmit {
                            onSearch(searchQuery)
                            isEditing.toggle()
                        }
                        .submitLabel(.search)

//                    if showClearButton {
                    if !searchQuery.isEmpty {
                        Button(action: {
                            self.searchQuery = ""
                            onClearClick()
                        }) {
                            Image(systemName: "multiply.circle.fill")
                                .foregroundColor(Color("primary"))
                        }
                    }
                }
                .padding(.horizontal, 16)
            }
            .frame(height: 50)
            .onAppear {
                isEditing.toggle()
            }

            Button(action: onCancelClick) {
                Text("Cancel")
                    .font(.body)
            }
            .buttonStyle(.plain)
            .tint(Color("primary"))
        }
    }
}

#Preview {
    VStack {
        SearchBar(
            onSearch: { _ in },
            onClearClick: {}, 
            onCancelClick: {},
            searchQuery: ""
        )

        SearchBar(
            onSearch: { _ in },
            onClearClick: {},
            onCancelClick: {},
            searchQuery: "qwerty"
        )
    }
}
