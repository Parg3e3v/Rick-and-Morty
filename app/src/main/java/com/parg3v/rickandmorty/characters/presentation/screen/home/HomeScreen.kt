package com.parg3v.rickandmorty.characters.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = koinViewModel()) {
    val characters = viewModel.charactersState.collectAsState()

    LazyColumn(modifier = modifier) {
        items(characters.value, key = { it.id }) {
            Text(text = it.name)
        }
    }
}