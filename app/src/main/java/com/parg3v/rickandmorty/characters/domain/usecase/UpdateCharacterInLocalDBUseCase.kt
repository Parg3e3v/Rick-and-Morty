package com.parg3v.rickandmorty.characters.domain.usecase

import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.flow.Flow

interface UpdateCharacterInLocalDBUseCase {
    suspend fun invoke(
        id: String,
        character: CharacterDomainModel,
    ): Flow<Result<CharacterDomainModel>>
}

class UpdateCharacterInLocalDBUseCaseImpl(private val repository: CharactersRepository) :
    UpdateCharacterInLocalDBUseCase {
    override suspend fun invoke(id: String, character: CharacterDomainModel) =
        repository.updateCharacterInLocalDB(id, character)
}