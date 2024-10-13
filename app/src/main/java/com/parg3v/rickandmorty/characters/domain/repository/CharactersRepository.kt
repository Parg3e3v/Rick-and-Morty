package com.parg3v.rickandmorty.characters.domain.repository

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(): Flow<List<CharacterDomainModel>>
}