package com.parg3v.rickandmorty.characters.presentation.screen.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(modifier: Modifier = Modifier, viewModel: LocationsViewModel = koinViewModel()) {
    val locations = viewModel.locationsState.collectAsState()

    LazyColumn(modifier = modifier) {
        items(locations.value, key = { it.id }) {
            Text(text = it.name)
        }
    }
}