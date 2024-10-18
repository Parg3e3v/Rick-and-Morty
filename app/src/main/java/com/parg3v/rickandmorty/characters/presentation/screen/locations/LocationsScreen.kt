package com.parg3v.rickandmorty.characters.presentation.screen.locations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.common_domain.Result
import com.parg3v.rickandmorty.common_presentation.common_error.ErrorComposable
import com.parg3v.rickandmorty.common_presentation.common_loading.LoadingComposable
import com.parg3v.rickandmorty.common_presentation.util.RickAndMortyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = koinViewModel(),
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val locations = viewModel.locationsState.collectAsState()

    when (val result = locations.value) {
        is Result.Failure -> {
            ErrorComposable(modifier = modifier, result = result)
        }

        is Result.Loading -> {
            LoadingComposable(modifier = modifier)
        }

        is Result.Success -> {
            LocationsColumn(modifier = modifier, lazyListState = lazyListState, result = result)
        }
    }
}

@Composable
fun LocationsColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    result: Result.Success<List<LocationDomainModel>>,
) {
    LazyColumn(modifier = modifier, state = lazyListState) {
        items(result.data, key = { it.id }) {
            Text(text = it.name)
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
            LocationsColumn(
                modifier = Modifier.fillMaxSize(),
                result = Result.Success(
                    listOf(
                        LocationDomainModel(
                            id = "1", name = "Earth", type = "Planet", dimension = "Dimension C-137"
                        ), LocationDomainModel(
                            id = "2", name = "Abadango", type = "Cluster", dimension = "unknown"
                        ), LocationDomainModel(
                            id = "3",
                            name = "Citadel of Ricks",
                            type = "Space station",
                            dimension = "unknown"
                        ), LocationDomainModel(
                            id = "4",
                            name = "Worldender's lair",
                            type = "Planet",
                            dimension = "unknown"
                        ), LocationDomainModel(
                            id = "5",
                            name = "Anatomy Park",
                            type = "Microverse",
                            dimension = "Dimension C-137"
                        )
                    )
                )
            )
        }
    }
}

