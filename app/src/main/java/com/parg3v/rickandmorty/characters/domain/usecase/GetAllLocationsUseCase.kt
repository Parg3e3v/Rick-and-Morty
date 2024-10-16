package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetAllLocationsUseCase {
    suspend fun invoke(): Flow<Result<List<LocationDomainModel>>>
}

class GetAllLocationsUseCaseImpl(private val repository: CharactersRepository) :
    GetAllLocationsUseCase {
    override suspend fun invoke(): Flow<Result<List<LocationDomainModel>>> = flow {
        emit(Result.Loading)
        repository.getAllLocations()
            .catch { exception ->
                emit(Result.Failure(exception.message))
            }
            .collect { emit(it) }
    }.flowOn(Dispatchers.IO)
}