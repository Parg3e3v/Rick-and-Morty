package com.parg3v.rickandmorty.characters.data.repository

import com.apollographql.apollo.ApolloClient
import com.parg3v.rickandmorty.GetAllCharactersQuery
import com.parg3v.rickandmorty.GetAllLocationsQuery
import com.parg3v.rickandmorty.GetCharacterByIdQuery
import com.parg3v.rickandmorty.GetResidentsQuery
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDataModel
import com.parg3v.rickandmorty.characters.data.mapper.toCharacterDomainModel
import com.parg3v.rickandmorty.characters.data.mapper.toLocationDataModel
import com.parg3v.rickandmorty.characters.data.mapper.toLocationDomainModel
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersRepositoryImpl(
    private val apolloClient: ApolloClient,
) : CharactersRepository {
    override suspend fun getAllCharacters(): Flow<Result<List<CharacterDomainModel>>> = flow {
        val response = apolloClient.query(GetAllCharactersQuery()).execute()
        try {
            val data = response.data?.characters?.results?.map { it.toCharacterDataModel() }

            data?.let {
                emit(Result.Success(data.map { it.toCharacterDomainModel() }))
            } ?: run {
                emit(Result.Failure("No data available"))
            }

        } catch (e: Exception) {
            emit(Result.Failure(e.message))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun getAllLocations(): Flow<Result<List<LocationDomainModel>>> = flow {
        val response = apolloClient.query(GetAllLocationsQuery()).execute()
        try {
            val data =
                response.data?.locations?.results?.map { it.toLocationDataModel() }

            data?.let {
                emit(Result.Success(data.map { it.toLocationDomainModel() }))
            } ?: run {
                emit(Result.Failure("No data available"))
            }
        } catch (e: Exception) {
            emit(Result.Failure(e.message))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun getCharacterById(characterId: String): Flow<Result<CharacterDomainModel>> =
        flow {
            val response = apolloClient.query(GetCharacterByIdQuery(characterId)).execute()
            try {
                val data = response.data?.character?.toCharacterDataModel()
                data?.let {
                    emit(Result.Success(data.toCharacterDomainModel()))
                } ?: run {
                    emit(Result.Failure("No data available"))
                }
            } catch (e: Exception) {
                emit(Result.Failure(e.message))
            }
        }

    override suspend fun getResidents(locationId: String): Flow<Result<Pair<String,List<CharacterDomainModel>>>> =
        flow {
            val response = apolloClient.query(GetResidentsQuery(locationId)).execute()
            try {
                val data = response.data?.location?.residents?.map { it.toCharacterDataModel() }

                val name = response.data?.location?.name ?: throw IllegalStateException("No name available")

                data?.let {
                    emit(Result.Success(Pair(name,data.map { it.toCharacterDomainModel() } )))
                } ?: run {
                    emit(Result.Failure("No data available"))
                }
            } catch (e: Exception) {
                emit(Result.Failure(e.message))
            }
        }
}