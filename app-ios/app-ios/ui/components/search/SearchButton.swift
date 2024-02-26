//
//  SearchButton.swift
//  app-ios
//
//  Created by Sebastian Owodzin on 26/02/2024.
//

import SwiftUI

struct SearchButton: View {
    let onClick: () -> Void

    var body: some View {
        Button(action: {
            onClick()
        }) {
            Image(systemName: "magnifyingglass")
        }
        .buttonStyle(.borderedProminent)
        .buttonBorderShape(.circle)
        .controlSize(.large)
        .tint(Color("primary"))
    }
}

#Preview {
    SearchButton {}
}
