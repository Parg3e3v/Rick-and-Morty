package com.parg3v.rickandmorty.characters.presentation.screen.residents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.presentation.screen.common.BackButton
import com.parg3v.rickandmorty.characters.presentation.screen.common.CharactersColumn
import com.parg3v.rickandmorty.common_domain.Result
import com.parg3v.rickandmorty.common_presentation.common_error.ErrorComposable
import com.parg3v.rickandmorty.common_presentation.common_loading.LoadingComposable
import com.parg3v.rickandmorty.common_presentation.util.RickAndMortyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ResidentsScreen(
    modifier: Modifier = Modifier,
    locationId: String,
    viewModel: ResidentsViewModel = koinViewModel(),
    onButtonPress: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    val characters = viewModel.charactersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getResidents(locationId)
    }
    Column {
        BackButton(onButtonPress = onButtonPress)
        when (val result = characters.value) {
            is Result.Failure -> {
                ErrorComposable(modifier = modifier, result = result)
            }

            is Result.Loading -> {
                LoadingComposable(modifier = modifier)
            }

            is Result.Success -> {
                ResidentsComposable(
                    modifier = modifier,
                    characters = result.data.second,
                    locationName = result.data.first,
                    onItemClick = onItemClick
                )
            }
        }
    }

}

@Composable
fun ResidentsComposable(
    modifier: Modifier = Modifier,
    characters: List<CharacterDomainModel>,
    locationName: String,
    onItemClick: (String) -> Unit,
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            text = "Residents of $locationName",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        CharactersColumn(
            modifier = modifier,
            characters = characters,
            favouriteCharacters = emptyMap(), // TODO: map
            onItemClick = onItemClick
        )
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
            Column {
                BackButton(onButtonPress = {})
                ResidentsComposable(modifier = Modifier.fillMaxSize(),
                    characters = listOf(
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
                        ), CharacterDomainModel(
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
                        ), CharacterDomainModel(
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
                    locationName = "Citadel of Ricks",
                    onItemClick = {})
            }
        }
    }
}