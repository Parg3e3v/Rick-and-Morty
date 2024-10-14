package com.parg3v.rickandmorty.characters.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersViewModel
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsViewModel
import org.koin.dsl.module

var charactersPresentationModule = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::LocationsViewModel)
}