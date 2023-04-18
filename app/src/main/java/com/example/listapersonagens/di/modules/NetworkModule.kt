package com.example.listapersonagens.di.modules

import com.example.listapersonagens.config.RetrofitConfig
import com.example.listapersonagens.config.factory.UrlFactory
import com.example.listapersonagens.network.service.DisneyService
import com.example.listapersonagens.network.service.RickyAndMortyService
import org.koin.dsl.module

val networkModule = module {
    single<DisneyService> {
        RetrofitConfig
            .getInstance(UrlFactory.BASE_URL_DISNEY)
            .create(DisneyService::class.java)
    }
    single<RickyAndMortyService> {
        RetrofitConfig
            .getInstance(UrlFactory.BASE_URL_RM)
            .create(RickyAndMortyService::class.java)
    }
}
