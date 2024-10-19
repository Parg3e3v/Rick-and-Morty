package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface GetResidentsUseCase {
    suspend fun invoke(locationId: String): Flow<Result<Pair<String,List<CharacterDomainModel>>>>
}

class GetResidentsUseCaseImpl(private val repository: CharactersRepository) : GetResidentsUseCase {
    override suspend fun invoke(locationId: String) = repository.getResidents(locationId)
}