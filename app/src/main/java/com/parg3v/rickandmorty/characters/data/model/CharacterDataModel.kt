package com.parg3v.rickandmorty.characters.data.model

data class CharacterDataModel(
    val id: String?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: String?,
    val location: String?,
    val image: String?,
    val episodes: List<EpisodeDataModel?>,
    val favourite: Boolean
)

data class EpisodeDataModel(
    val name: String?,
    val episode: String?,
)