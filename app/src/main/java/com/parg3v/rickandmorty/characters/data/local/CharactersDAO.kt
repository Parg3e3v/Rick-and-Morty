package com.parg3v.rickandmorty.characters.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterEntity?

    @Query("UPDATE character SET favourite = :isFavourite WHERE id = :id")
    suspend fun updateCharacterFavourite(id: String, isFavourite: Boolean)
}