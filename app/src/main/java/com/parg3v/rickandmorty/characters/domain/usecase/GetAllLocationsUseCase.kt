package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

interface GetAllLocationsUseCase {
    suspend fun invoke(): Flow<List<LocationDomainModel>>
}

class GetAllLocationsUseCaseImpl(private val repository: CharactersRepository) : GetAllLocationsUseCase {
    override suspend fun invoke() = repository.getAllLocations()
}