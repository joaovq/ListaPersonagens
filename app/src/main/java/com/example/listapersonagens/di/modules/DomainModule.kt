package com.example.listapersonagens.di.modules

import com.example.listapersonagens.di.usecase.SignInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SignInUseCase(get()) }
}
