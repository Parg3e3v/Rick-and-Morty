package com.parg3v.rickandmorty.characters.presentation.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersScreen
import com.parg3v.rickandmorty.characters.presentation.screen.details.DetailsScreen
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsScreen

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
                navController.navigate(Destination.DetailsDestination(id)) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        }
        composable<Destination.LocationsDestination> {
            LocationsScreen(lazyListState = locationsLazyListState)
        }
        composable<Destination.DetailsDestination> { backStackEntry ->
            val details: Destination.DetailsDestination = backStackEntry.toRoute()

            DetailsScreen(
                characterId = details.characterId,
                onButtonPress = { navController.popBackStack() })
        }
    }
}