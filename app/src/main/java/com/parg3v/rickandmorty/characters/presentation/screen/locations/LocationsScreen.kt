package com.parg3v.rickandmorty.characters.presentation.screen.locations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
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
    onClick: (id: String) -> Unit,
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
            LocationsColumn(
                modifier = modifier,
                lazyListState = lazyListState,
                result = result,
                onClick = onClick
            )
        }
    }
}

@Composable
fun LocationsColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    result: Result.Success<List<LocationDomainModel>>,
    onClick: (id: String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize(), state = lazyListState) {
        items(result.data, key = { it.id }) { location ->
            LocationItem(
                name = location.name,
                type = location.type,
                dimension = location.dimension,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick(location.id) }
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
            val locations = listOf(
                LocationDomainModel("1", "Earth (C-137)", "Planet", "Dimension C-137"),
                LocationDomainModel("2", "Abadango", "Cluster", "unknown"),
                LocationDomainModel("3", "Citadel of Ricks", "Space station", "unknown"),
                LocationDomainModel("4", "Worldender's lair", "Planet", "unknown"),
                LocationDomainModel("5", "Anatomy Park", "Microverse", "Dimension C-137"),
                LocationDomainModel("6", "Interdimensional Cable", "TV", "unknown"),
                LocationDomainModel("7", "Immortality Field Resort", "Resort", "unknown"),
                LocationDomainModel(
                    "8", "Post-Apocalyptic Earth", "Planet", "Post-Apocalyptic Dimension"
                ),
                LocationDomainModel("9", "Purge Planet", "Planet", "Replacement Dimension"),
                LocationDomainModel("10", "Venzenulon 7", "Planet", "unknown"),
                LocationDomainModel("11", "Bepis 9", "Planet", "unknown"),
                LocationDomainModel("12", "Cronenberg Earth", "Planet", "Cronenberg Dimension"),
                LocationDomainModel("13", "Nuptia 4", "Planet", "unknown"),
                LocationDomainModel("14", "Giant's Town", "Fantasy town", "Fantasy Dimension"),
                LocationDomainModel("15", "Bird World", "Planet", "unknown"),
                LocationDomainModel("16", "St. Gloopy Noops Hospital", "Space station", "unknown"),
                LocationDomainModel("17", "Earth (5-126)", "Planet", "Dimension 5-126"),
                LocationDomainModel("18", "Mr. Goldenfold's dream", "Dream", "Dimension C-137"),
                LocationDomainModel("19", "Gromflom Prime", "Planet", "Replacement Dimension"),
                LocationDomainModel(
                    "20", "Earth (Replacement Dimension)", "Planet", "Replacement Dimension"
                )
            )
            LocationsColumn(
                modifier = Modifier.fillMaxSize(),
                result = Result.Success(locations),
                onClick = {})
        }
    }
}

