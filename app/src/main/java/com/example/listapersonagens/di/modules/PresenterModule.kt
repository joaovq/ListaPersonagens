package com.example.listapersonagens.di.modules

import com.example.listapersonagens.ui.presenter.CharactersPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { CharactersPresenter(get(), get()) }
}
