package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

interface GetAllCharactersUseCase {
    suspend fun invoke(): Flow<List<CharacterDomainModel>>
}

class GetAllCharactersUseCaseImpl(private val repository: CharactersRepository) :
    GetAllCharactersUseCase {

    override suspend fun invoke() = repository.getAllCharacters()

}