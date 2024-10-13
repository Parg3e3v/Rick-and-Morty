package com.parg3v.rickandmorty.characters.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.parg3v.rickandmorty.characters.presentation.screen.home.HomeViewModel
import org.koin.dsl.module

var charactersPresentationModule = module {
    viewModelOf(::HomeViewModel)
}