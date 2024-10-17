package com.parg3v.rickandmorty.characters.presentation.navigation

import androidx.annotation.DrawableRes
import com.parg3v.rickandmorty.R
import kotlinx.serialization.Serializable

sealed interface Destination{
    @Serializable
    data object CharactersDestination: Destination

    @Serializable
    data object LocationsDestination: Destination

    @Serializable
    data class DetailsDestination(val characterId: String): Destination

}

enum class TopLevelRoutes(
    val route: Destination,
    @DrawableRes val icon: Int,
    val label: Int,
){
    Characters(
        route = Destination.CharactersDestination,
        icon = R.drawable.character,
        label = R.string.bottom_navigation_characters
    ),

    Locations(
        route = Destination.LocationsDestination,
        icon = R.drawable.earth,
        label = R.string.bottom_navigation_locations
    )
}
