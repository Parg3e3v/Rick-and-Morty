package com.parg3v.rickandmorty.characters.domain.model

data class CharacterDomainModel(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: String
)