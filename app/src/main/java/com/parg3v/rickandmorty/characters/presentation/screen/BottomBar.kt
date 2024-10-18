package com.parg3v.rickandmorty.characters.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.parg3v.rickandmorty.characters.presentation.navigation.TopLevelRoutes
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    charactersLazyListState: LazyListState = rememberLazyListState(),
    locationsLazyListState: LazyListState = rememberLazyListState(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val coroutineScope = rememberCoroutineScope()

    val showBottomNav = TopLevelRoutes.entries.map { it.route::class }.any { route ->
        currentDestination?.hierarchy?.any {
            it.hasRoute(route)
        } == true
    }

    AnimatedVisibility(visible = showBottomNav, modifier = modifier.fillMaxWidth()) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            TopLevelRoutes.entries.map { bottomNavigationItem ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.hasRoute(bottomNavigationItem.route::class) } == true

                if (currentDestination != null) {
                    NavigationBarItem(selected = isSelected, icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(bottomNavigationItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }, label = {
                        Text(stringResource(bottomNavigationItem.label))
                    }, alwaysShowLabel = isSelected, onClick = {
                        if (isSelected) {
                            coroutineScope.launch {
                                when (navController.currentDestination?.route) {
                                    TopLevelRoutes.Characters.route::class.qualifiedName -> {
                                        charactersLazyListState.animateScrollToItem(0)
                                    }

                                    TopLevelRoutes.Locations.route::class.qualifiedName -> {
                                        locationsLazyListState.animateScrollToItem(0)
                                    }
                                }
                            }
                        } else {
                            navController.navigate(bottomNavigationItem.route) {
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

                    })
                }
            }
        }
    }
}