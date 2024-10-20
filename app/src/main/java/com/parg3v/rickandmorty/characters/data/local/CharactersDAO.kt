package com.parg3v.rickandmorty.characters.data.local

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getCharactersFromLocalDB(): Map<@MapColumn(columnName = "id") String, CharacterEntity>

    @Upsert
    suspend fun updateCharacter(character: CharacterEntity)
}