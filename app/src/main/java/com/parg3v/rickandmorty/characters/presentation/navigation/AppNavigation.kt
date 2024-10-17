package com.parg3v.rickandmorty.characters.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersScreen
import com.parg3v.rickandmorty.characters.presentation.screen.details.DetailsScreen
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.CharactersDestination
    ) {
        composable<Destination.CharactersDestination> {
            CharactersScreen { id ->
                navController.navigate(
                    Destination.DetailsDestination(
                        characterId = id
                    )
                )
            }
        }
        composable<Destination.LocationsDestination> {
            LocationsScreen()
        }
        composable<Destination.DetailsDestination> { backStackEntry ->
            val details: Destination.DetailsDestination = backStackEntry.toRoute()

            DetailsScreen(characterId = details.characterId)
        }
    }
}