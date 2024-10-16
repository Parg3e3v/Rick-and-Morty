package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetAllCharactersUseCase {
    suspend fun invoke(): Flow<Result<List<CharacterDomainModel>>>
}

class GetAllCharactersUseCaseImpl(private val repository: CharactersRepository) :
    GetAllCharactersUseCase {
    override suspend fun invoke(): Flow<Result<List<CharacterDomainModel>>> = flow {
        emit(Result.Loading)
        repository.getAllCharacters()
            .catch { exception ->
                emit(Result.Failure(exception.message))
            }
            .collect { emit(it) }
    }.flowOn(Dispatchers.IO)
}