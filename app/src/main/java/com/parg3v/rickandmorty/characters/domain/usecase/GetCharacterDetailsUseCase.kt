package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface GetCharacterDetailsUseCase {
    suspend fun invoke(characterId: String): Flow<Result<CharacterDomainModel>>
}

class GetCharacterDetailsUseCaseImpl(private val repository: CharactersRepository) : GetCharacterDetailsUseCase {
    override suspend fun invoke(characterId: String) = repository.getCharacterById(characterId)
}