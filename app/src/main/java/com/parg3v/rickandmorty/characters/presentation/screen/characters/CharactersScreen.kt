package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.common_domain.Result
import com.parg3v.rickandmorty.common_presentation.common_error.ErrorComposable
import com.parg3v.rickandmorty.common_presentation.common_loading.LoadingComposable
import com.parg3v.rickandmorty.common_presentation.util.RickAndMortyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = koinViewModel(),
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClick: (id: String) -> Unit,
) {
    val characters = viewModel.charactersState.collectAsState()


    when (val result = characters.value) {
        is Result.Failure -> {
            ErrorComposable(modifier = modifier, result = result)
        }

        is Result.Loading -> {
            LoadingComposable(modifier = modifier)
        }

        is Result.Success -> {
            CharactersColumn(
                modifier = modifier,
                lazyListState = lazyListState,
                result = result,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun CharactersColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    result: Result.Success<List<CharacterDomainModel>>,
    onItemClick: (id: String) -> Unit,
) {
    LazyColumn(modifier = modifier, state = lazyListState) {
        items(result.data, key = { it.id }) { character ->
            CharacterItem(
                modifier = Modifier.padding(8.dp), character = character, onItemClick = onItemClick
            )
        }
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    RickAndMortyTheme {
        Surface {
            ErrorComposable(result = Result.Failure("failure message"))
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    RickAndMortyTheme {
        Surface {
            LoadingComposable()
        }
    }
}

@Preview
@Composable
private fun SuccessPreview() {
    RickAndMortyTheme {
        Surface {
            CharactersColumn(
                modifier = Modifier.fillMaxSize(),
                result = Result.Success(
                    listOf(
                        CharacterDomainModel(
                            id = "1",
                            name = "Rick Sanchez",
                            status = "Alive",
                            species = "Human",
                            type = "",
                            gender = "Male",
                            origin = "Earth (C-137)",
                            location = "Citadel of Ricks",
                            image = "",
                            episodes = emptyList()
                        ),
                        CharacterDomainModel(
                            id = "2",
                            name = "Morty Smith",
                            status = "Alive",
                            species = "Human",
                            type = "",
                            gender = "Male",
                            origin = "Earth (C-137)",
                            location = "Citadel of Ricks",
                            image = "",
                            episodes = emptyList()
                        ),
                        CharacterDomainModel(
                            id = "3",
                            name = "Summer Smith",
                            status = "Alive",
                            species = "Human",
                            type = "",
                            gender = "Female",
                            origin = "Earth (C-137)",
                            location = "Citadel of Ricks",
                            image = "",
                            episodes = emptyList()
                        )
                    )
                ),
                onItemClick = {}
            )
        }
    }
}
