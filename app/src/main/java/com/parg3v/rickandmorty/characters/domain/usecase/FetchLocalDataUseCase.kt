package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.data.local.CharacterEntity
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface FetchLocalDataUseCase {
    suspend fun invoke(id: String): Flow<Result<CharacterEntity?>>
}

class FetchLocalDataUseCaseImpl(private val repository: CharactersRepository) : FetchLocalDataUseCase {
    override suspend fun invoke(id: String) = repository.getCharacterFromLocalDb(id)
}