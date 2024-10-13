package com.parg3v.rickandmorty.characters.domain.di

import com.parg3v.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import com.parg3v.rickandmorty.characters.domain.usecase.GetAllCharactersUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val charactersDomainModule = module {
    factoryOf(::GetAllCharactersUseCaseImpl) { bind<GetAllCharactersUseCase> () }
}