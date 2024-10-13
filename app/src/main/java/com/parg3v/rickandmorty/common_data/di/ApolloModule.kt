package com.parg3v.rickandmorty.common_data.di

import com.apollographql.apollo.ApolloClient
import com.parg3v.rickandmorty.common_data.util.BASE_URL
import org.koin.dsl.module

val apolloModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .build()
    }
}