package com.example.listapersonagens.di

import com.example.listapersonagens.config.RetrofitConfig
import com.example.listapersonagens.config.factory.UrlFactory
import com.example.listapersonagens.network.service.DisneyService
import com.example.listapersonagens.network.service.RickyAndMortyService
import retrofit2.Retrofit

class AppContainer {
    private val retrofitDisney: Retrofit = RetrofitConfig
        .getInstance(UrlFactory.BASE_URL_DISNEY)
    val disneyService: DisneyService = retrofitDisney.create(DisneyService::class.java)

    private val retrofitRickyAndMorty: Retrofit = RetrofitConfig
        .getInstance(UrlFactory.BASE_URL_RM)
    val rickyAndMortyService: RickyAndMortyService =
        retrofitRickyAndMorty.create(RickyAndMortyService::class.java)
}
