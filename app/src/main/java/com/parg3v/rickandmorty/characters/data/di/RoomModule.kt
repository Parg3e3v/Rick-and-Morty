package com.parg3v.rickandmorty.characters.data.di

import android.app.Application
import androidx.room.Room
import com.parg3v.rickandmorty.characters.data.local.CharacterDao
import com.parg3v.rickandmorty.common_data.local.AppDatabase
import org.koin.dsl.module

val roomModule = module {
    single { providesDatabase(get()) }
    single { providesDao(get()) }
}

fun providesDatabase(application: Application): AppDatabase = Room.databaseBuilder(
    application, AppDatabase::class.java, "app_database"
).fallbackToDestructiveMigration().build()

fun providesDao(db: AppDatabase): CharacterDao = db.characterDao()
