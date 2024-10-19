package com.parg3v.rickandmorty.characters.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersViewModel
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsViewModel
import com.parg3v.rickandmorty.characters.presentation.screen.details.DetailsViewModel
import com.parg3v.rickandmorty.characters.presentation.screen.residents.ResidentsViewModel
import org.koin.dsl.module

var charactersPresentationModule = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::LocationsViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::ResidentsViewModel)
}