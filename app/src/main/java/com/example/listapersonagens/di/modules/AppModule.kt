package com.example.listapersonagens.di.modules

import com.example.listapersonagens.network.authentication.Authenticator
import com.example.listapersonagens.network.authentication.BackendAuthenticator
import org.koin.dsl.module

val appModule = module {
    single<Authenticator> { BackendAuthenticator }
}
