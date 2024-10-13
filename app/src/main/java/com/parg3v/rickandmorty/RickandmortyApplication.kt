package com.parg3v.rickandmorty

import android.app.Application
import com.parg3v.rickandmorty.characters.data.di.charactersDataModule
import com.parg3v.rickandmorty.characters.domain.di.charactersDomainModule
import com.parg3v.rickandmorty.characters.presentation.di.charactersPresentationModule
import com.parg3v.rickandmorty.common_data.di.apolloModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickandmortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RickandmortyApplication)

            modules(
                apolloModule,
                charactersDataModule,
                charactersDomainModule,
                charactersPresentationModule
            )
        }

    }
}