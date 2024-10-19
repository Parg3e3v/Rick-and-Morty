package com.parg3v.rickandmorty.characters.domain.model

data class CharacterDomainModel(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episodes: List<EpisodeDomainModel?>,
    val favourite: Boolean
)

data class EpisodeDomainModel(
    val name: String,
    val episode: String,
)