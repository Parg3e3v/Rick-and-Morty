package com.parg3v.rickandmorty.characters.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersScreen
import com.parg3v.rickandmorty.characters.presentation.screen.common.BackButton
import com.parg3v.rickandmorty.characters.presentation.screen.details.DetailsScreen
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsScreen
import com.parg3v.rickandmorty.characters.presentation.screen.residents.ResidentsScreen
import com.parg3v.rickandmorty.common_domain.Result
import com.parg3v.rickandmorty.common_presentation.common_error.ErrorComposable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier, navController: NavHostController,
    lazyListState: LazyListState = rememberLazyListState(),
    locationsLazyListState: LazyListState = rememberLazyListState(),
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.CharactersDestination
    ) {
        composable<Destination.CharactersDestination> {
            CharactersScreen(lazyListState = lazyListState) { id ->
                navController.navigate(Destination.DetailsDestination(id))
            }
        }
        composable<Destination.LocationsDestination> {
            LocationsScreen(lazyListState = locationsLazyListState, onClick = { id ->
                navController.navigate(Destination.ResidentsDestination(id))
            })
        }
        composable<Destination.DetailsDestination> { backStackEntry ->
            val details: Destination.DetailsDestination = backStackEntry.toRoute()

            if (details.characterId.isNotBlank()) {
                DetailsScreen(
                    characterId = details.characterId,
                    onButtonPress = { navController.popBackStack() })
            } else {
                Column {
                    BackButton(onButtonPress = { navController.popBackStack() })
                    ErrorComposable(result = Result.Failure("Unexpected error"))
                }
            }

        }
        composable<Destination.ResidentsDestination> { backStackEntry ->
            val residents: Destination.ResidentsDestination = backStackEntry.toRoute()

            if (residents.locationId.isNotBlank()) {

                ResidentsScreen(locationId = residents.locationId, onButtonPress = {
                    navController.popBackStack()
                }) { id ->
                    navController.navigate(Destination.DetailsDestination(id))
                }
            } else {
                Column {
                    BackButton(onButtonPress = { navController.popBackStack() })
                    ErrorComposable(result = Result.Failure("Unexpected error"))
                }
            }
        }

    }
}