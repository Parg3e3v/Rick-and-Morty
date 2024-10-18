package com.parg3v.rickandmorty.characters.presentation.screen.details

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.EpisodeDomainModel
import com.parg3v.rickandmorty.common_domain.Result
import com.parg3v.rickandmorty.common_presentation.common_error.ErrorComposable
import com.parg3v.rickandmorty.common_presentation.common_loading.LoadingComposable
import com.parg3v.rickandmorty.common_presentation.util.RickAndMortyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    characterId: String,
    viewModel: DetailsViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacterDetails(characterId)
    }

    val character = viewModel.character.collectAsState()

    when (val result = character.value) {
        is Result.Failure -> {
            ErrorComposable(modifier = modifier, result = result)
        }

        is Result.Loading -> {
            LoadingComposable(modifier = modifier)
        }

        is Result.Success -> {
            DetailsComposable(modifier = modifier, result = result)
        }
    }
}


@Composable
fun DetailsComposable(
    modifier: Modifier = Modifier,
    result: Result.Success<CharacterDomainModel?>,
) {
    result.data?.let {
        LazyColumn(modifier = modifier) {
            item {
                Text(text = result.data.toString())
            }
        }
    } ?: run {
        ErrorComposable(result = Result.Failure("No data available"))
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
            DetailsComposable(
                result = Result.Success(
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
                        episode = listOf(
                            EpisodeDomainModel(
                                name = "Pilot", episode = "S01E01"
                            ),
                            EpisodeDomainModel(
                                name = "Pilot", episode = "S01E01"
                            ),
                            EpisodeDomainModel(
                                name = "Pilot", episode = "S01E01"
                            ),
                            EpisodeDomainModel(
                                name = "Pilot", episode = "S01E01"
                            ),
                            EpisodeDomainModel(
                                name = "Pilot", episode = "S01E01"
                            ),
                        )
                    )
                )
            )
        }
    }
}