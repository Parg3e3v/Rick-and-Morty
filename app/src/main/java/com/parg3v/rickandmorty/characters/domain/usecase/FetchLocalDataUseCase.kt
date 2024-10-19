package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface FetchLocalDataUseCase {
    suspend fun invoke(): Flow<Result<Map<String, CharacterDomainModel>>>
}

class FetchLocalDataUseCaseImpl(private val repository: CharactersRepository) :
    FetchLocalDataUseCase {
    override suspend fun invoke() = repository.getCharactersFromLocalDb()
}