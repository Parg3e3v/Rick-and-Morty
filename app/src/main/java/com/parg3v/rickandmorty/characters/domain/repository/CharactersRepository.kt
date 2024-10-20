package com.parg3v.rickandmorty.characters.domain.repository

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(): Flow<Result<List<CharacterDomainModel>>>
    suspend fun getAllLocations(): Flow<Result<List<LocationDomainModel>>>
    suspend fun getCharacterById(characterId: String): Flow<Result<CharacterDomainModel>>
    suspend fun getResidents(locationId: String): Flow<Result<Pair<String, List<CharacterDomainModel>>>>

    suspend fun getCharactersFromLocalDb(): Flow<Result<Map<String, CharacterDomainModel>>>
    suspend fun updateCharacterInLocalDB(
        id: String,
        character: CharacterDomainModel,
    ): Flow<Result<CharacterDomainModel>>
}