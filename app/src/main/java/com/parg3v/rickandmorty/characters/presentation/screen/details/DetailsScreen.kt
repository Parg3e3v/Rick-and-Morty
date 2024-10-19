package com.parg3v.rickandmorty.characters.presentation.screen.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.parg3v.rickandmorty.R
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
    onButtonPress: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacterDetails(characterId)
    }

    val character = viewModel.character.collectAsState()

    Box {
        Button(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(5.dp), onClick = onButtonPress
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
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
}


@Composable
fun DetailsComposable(
    modifier: Modifier = Modifier,
    result: Result.Success<CharacterDomainModel?>,
) {
    result.data?.let {

        DetailsScreenComposable(modifier = modifier, character = it)

    } ?: run {
        ErrorComposable(result = Result.Failure("No data available"))
    }
}

@Composable
fun DetailsScreenComposable(modifier: Modifier = Modifier, character: CharacterDomainModel) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val episodeTableHeight = screenHeight * 0.35f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val description = buildString {
            append("Status: ${character.status} · ${character.species}")
            if (character.type.isNotEmpty()) {
                append(" (${character.type})")
            }
        }

        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = character.image,
            contentDescription = null,
            placeholder = painterResource(R.drawable.placeholder)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = character.name,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp, top = 8.dp),
            text = "Gender: ${character.gender}",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                painter = painterResource(R.drawable.earth),
                contentDescription = null
            )
            Text("Origin: ${character.origin}", style = MaterialTheme.typography.labelMedium)
        }
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            Text(
                "Last known location: ${character.location}",
                style = MaterialTheme.typography.labelMedium
            )
        }

        EpisodeTable(
            Modifier
                .height(episodeTableHeight)
                .weight(1F)
                .padding(horizontal = 1.dp)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)),
            character.episodes
        )
    }
}


@Composable
fun EpisodeTable(modifier: Modifier = Modifier, episodes: List<EpisodeDomainModel?>) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(text = "Name", modifier = Modifier.weight(1f))
                TableCell(text = "Episode", modifier = Modifier
                    .weight(0.3f)
                    .wrapContentWidth())
            }
        }
        item {
            HorizontalDivider(thickness = 1.dp)
        }
        items(episodes) { episode ->
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(text = episode?.name ?: "-", modifier = Modifier.weight(1f))
                TableCell(
                    text = episode?.episode ?: "-",
                    modifier = Modifier
                        .weight(0.3f)
                        .wrapContentWidth()
                )
            }
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(8.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
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
            DetailsComposable(
                modifier = Modifier.fillMaxSize(), result = Result.Success(
                    CharacterDomainModel(
                        id = "1",
                        name = "Rick Sanchez",
                        status = "unknown",
                        species = "Human",
                        type = "human with antennae",
                        gender = "Male",
                        origin = "Earth (C-137)",
                        location = "Citadel of Ricks",
                        image = "",
                        episodes = listOf(
                            EpisodeDomainModel(
                                name = "PilotPilotPilotPilotPilotPilotPilotPilotPilotPilotPilotPilotPilotPilotPilot",
                                episode = "S01E01"
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