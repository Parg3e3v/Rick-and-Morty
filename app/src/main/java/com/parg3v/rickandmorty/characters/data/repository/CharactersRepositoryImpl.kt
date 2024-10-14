package com.parg3v.rickandmorty.characters.data.repository

import com.apollographql.apollo.ApolloClient
import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.GetAllLocationsQuery
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDataModel
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDomainModel
import com.parg3v.rickandmorty.characters.data.mapper.toLocationDataModel
import com.parg3v.rickandmorty.characters.data.mapper.toLocationDomainModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
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

    override suspend fun getAllLocations(): Flow<List<LocationDomainModel>> = flow {
        val response = apolloClient.query(GetAllLocationsQuery()).execute()
        val data =
            response.data?.locations?.results?.map { it.toLocationDataModel() } ?: emptyList()

        emit(data.map { it.toLocationDomainModel() })

    }.flowOn(Dispatchers.IO)
}