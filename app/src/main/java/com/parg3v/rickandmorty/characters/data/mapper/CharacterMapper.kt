package com.parg3v.rickandmorty.characters.data.mapper

import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.GetAllLocationsQuery
import com.parg3v.rickandmorty.GetCharacterByIdQuery
import com.parg3v.rickandmorty.GetResidentsQuery
import com.parg3v.rickandmorty.characters.data.local.CharacterEntity
import com.parg3v.rickandmorty.characters.data.model.CharacterDataModel
import com.parg3v.rickandmorty.characters.data.model.EpisodeDataModel
import com.parg3v.rickandmorty.characters.data.model.LocationDataModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.EpisodeDomainModel
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import kotlin.random.Random

fun CharacterDataModel.toCharacterDomainModel(): CharacterDomainModel = CharacterDomainModel(
    id = this.id ?: Random.nextInt().toString(),
    name = this.name ?: "Unknown",
    status = this.status ?: "Unknown",
    species = this.species ?: "Unknown",
    image = this.image ?: "",
    origin = this.origin ?: "Unknown",
    location = this.location ?: "Unknown",
    type = this.type ?: "Unknown",
    gender = this.gender ?: "Unknown",
    episodes = this.episodes.map { it.toEpisodeDomainModel() },
    favourite = this.favourite
)

fun EpisodeDataModel?.toEpisodeDomainModel(): EpisodeDomainModel = EpisodeDomainModel(
    name = this?.name ?: "-",
    episode = this?.episode ?: "-"
)

fun CharacterDomainModel.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    image = this.image,
    origin = this.origin,
    location = this.location,
    type = this.type,
    gender = this.gender,
    episodes = this.episodes.map { it?.toEpisodeDataModel() },
    favourite = this.favourite
)

fun EpisodeDomainModel.toEpisodeDataModel(): EpisodeDataModel = EpisodeDataModel(
    name = this.name,
    episode = this.episode
)

/*-------------------------------------------------------------------------------------------*/

fun GetAllCharactersQuery.Result?.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = this?.id,
    name = this?.name,
    status = this?.status,
    species = this?.species,
    image = this?.image,
    origin = this?.origin?.name,
    location = null,
    type = null,
    gender = null,
    episodes = emptyList(),
    favourite = false
)

/*-------------------------------------------------------------------------------------------*/

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

/*-------------------------------------------------------------------------------------------*/

fun GetCharacterByIdQuery.Character?.toCharacterDataModel(): CharacterDataModel =
    CharacterDataModel(
        id = this?.id,
        name = this?.name,
        status = this?.status,
        species = this?.species,
        image = this?.image,
        origin = this?.origin?.name,
        location = this?.location?.name,
        type = this?.type,
        gender = this?.gender,
        episodes = this?.episode?.map { it?.toEpisodeDataModel() } ?: emptyList(),
        favourite = false
    )

fun GetCharacterByIdQuery.Episode?.toEpisodeDataModel(): EpisodeDataModel = EpisodeDataModel(
    name = this?.name,
    episode = this?.episode
)

/*-------------------------------------------------------------------------------------------*/

fun GetResidentsQuery.Resident?.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = this?.id,
    name = this?.name,
    status = this?.status,
    species = this?.species,
    image = this?.image,
    origin = this?.origin?.name,
    location = null,
    type = null,
    gender = null,
    episodes = emptyList(),
    favourite = false
)

/*-------------------------------------------------------------------------------------------*/

fun CharacterEntity.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = this.id,
    name = null,
    status = null,
    species = null,
    image = null,
    origin = null,
    location = null,
    type = null,
    gender = null,
    episodes = emptyList(),
    favourite = this.favourite
)

fun CharacterDataModel.toCharacterEntity(id: String): CharacterEntity = CharacterEntity(
    id = id,
    name = this.name ?: "Unknown",
    status = this.status ?: "Unknown",
    species = this.species ?: "Unknown",
    image = this.image ?: "",
    origin = this.origin ?: "Unknown",
    location = this.location ?: "Unknown",
    type = this.type ?: "Unknown",
    gender = this.gender ?: "Unknown",
    favourite = this.favourite
)