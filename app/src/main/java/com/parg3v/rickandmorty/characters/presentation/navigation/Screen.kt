package com.parg3v.rickandmorty.characters.presentation.navigation

import androidx.annotation.DrawableRes
import com.parg3v.rickandmorty.R
import kotlinx.serialization.Serializable


@Serializable
object CharactersDestination

@Serializable
object LocationsDestination

data class TopLevelRoute<T : Any>(
    val route: T,
    @DrawableRes val icon: Int,
    val label: Int,
)

val TOP_LEVEL_ROUTES = listOf(
    TopLevelRoute(
        route = CharactersDestination,
        icon = R.drawable.character,
        label = R.string.bottom_navigation_characters
    ),

    TopLevelRoute(
        route = LocationsDestination,
        icon = R.drawable.earth,
        label = R.string.bottom_navigation_locations
    )
)
