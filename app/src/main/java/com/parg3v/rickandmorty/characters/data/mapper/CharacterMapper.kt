package com.parg3v.rickandmorty.characters.data.mapper

import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.characters.data.model.CharacterDataModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel

fun GetAllCharactersQuery.Result?.toCharacterDataModel(): CharacterDataModel = CharacterDataModel(
    id = requireNotNull(this?.id) { "Character ID is null" },
    name = requireNotNull(this?.name) { "Character name is null" },
    status = requireNotNull(this?.status) { "Character status is null" },
    species = requireNotNull(this?.species) { "Character species is null" },
    image = requireNotNull(this?.image) { "Character image is null" },
    origin = requireNotNull(this?.origin?.name) { "Character origin name is null" }
)


fun CharacterDataModel.toCharacterDomainModel(): CharacterDomainModel = CharacterDomainModel(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    image = this.image,
    origin = this.origin
)
