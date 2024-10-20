package com.parg3v.rickandmorty.common_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parg3v.rickandmorty.characters.data.local.CharacterDao
import com.parg3v.rickandmorty.characters.data.local.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}