package com.parg3v.rickandmorty.characters.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "favourite")
    val favourite: Boolean,
)