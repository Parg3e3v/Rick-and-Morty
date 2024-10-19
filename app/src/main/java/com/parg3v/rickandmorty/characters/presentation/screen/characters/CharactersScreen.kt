package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.presentation.screen.common.CharactersColumn
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
    val isCharacterFavourite = viewModel.isCharacterFavourite.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchLocalData("test")
    }

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
                characters = result.data,
                onItemClick = onItemClick,
                favouriteCharacters = isCharacterFavourite.value
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
                characters =
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
                        episodes = emptyList(),
                        favourite = false
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
                        episodes = emptyList(),
                        favourite = false
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
                        episodes = emptyList(),
                        favourite = false
                    )
                ),
                favouriteCharacters = emptyMap(),
                onItemClick = {}
            )
        }
    }
}
