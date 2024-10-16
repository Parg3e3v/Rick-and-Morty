package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.rickandmorty.common_domain.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = koinViewModel(),
) {
    val characters = viewModel.charactersState.collectAsState()


    when (val result = characters.value) {
        is Result.Failure -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text("Error: ${result.message}", modifier = Modifier.align(Alignment.Center))
            }
        }

        is Result.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        is Result.Success -> {
            LazyColumn(modifier = modifier) {
                items(result.data, key = { it.id }) { character ->
                    CharacterItem(modifier = Modifier.padding(8.dp), character = character)
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharactersScreenPreview() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }
}