package com.parg3v.rickandmorty.characters.data.di

import com.parg3v.rickandmorty.characters.domain.repository.CharactersRepository
import com.parg3v.rickandmorty.characters.data.repository.CharactersRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

var charactersDataModule = module {
    singleOf(::CharactersRepositoryImpl) {bind<CharactersRepository>()}
}