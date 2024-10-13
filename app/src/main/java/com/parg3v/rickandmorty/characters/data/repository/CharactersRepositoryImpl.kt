package com.parg3v.rickandmorty.characters.data.repository

import com.apollographql.apollo.ApolloClient
import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDataModel
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersRepositoryImpl(
    private val apolloClient: ApolloClient,
) : CharactersRepository {
    override suspend fun getAllCharacters(): Flow<List<CharacterDomainModel>> = flow {
        val response = apolloClient.query(GetAllCharactersQuery()).execute()
        val data =
            response.data?.characters?.results?.map { it.toCharacterDataModel() } ?: emptyList()

        emit(data.map { it.toCharacterDomainModel() })

    }.flowOn(Dispatchers.IO)
}