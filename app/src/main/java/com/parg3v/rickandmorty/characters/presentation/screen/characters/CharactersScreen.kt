package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(modifier: Modifier = Modifier, viewModel: CharactersViewModel = koinViewModel()) {
    val characters = viewModel.charactersState.collectAsState()

    LazyColumn(modifier = modifier) {
        items(characters.value, key = { it.id }) {
            CharacterItem(modifier = Modifier.padding(8.dp), character = it)
        }
    }
}