package com.parg3v.rickandmorty.characters.data.mapper

import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.GetAllLocationsQuery
import com.parg3v.rickandmorty.characters.data.model.CharacterDataModel
import com.parg3v.rickandmorty.characters.data.model.LocationDataModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import kotlin.random.Random

fun GetAllCharactersQuery.Result?.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = this?.id,
    name = this?.name,
    status = this?.status,
    species = this?.species,
    image = this?.image,
    origin = this?.origin?.name
)


fun CharacterDataModel.toCharacterDomainModel(): CharacterDomainModel = CharacterDomainModel(
    id = this.id ?: Random.nextInt().toString(),
    name = this.name ?: "Unknown",
    status = this.status ?: "Unknown",
    species = this.species ?: "Unknown",
    image = this.image ?: "",
    origin = this.origin ?: "Unknown"
)


fun GetAllLocationsQuery.Result?.toLocationDataModel(): LocationDataModel = LocationDataModel(
    id = this?.id,
    name = this?.name,
    type = this?.type,
    dimension = this?.dimension,
)

fun LocationDataModel.toLocationDomainModel(): LocationDomainModel = LocationDomainModel(
    id = this.id ?: Random.nextInt().toString(),
    name = this.name ?: "Unknown",
    type = this.type ?: "Unknown",
    dimension = this.dimension ?: "Unknown"
)