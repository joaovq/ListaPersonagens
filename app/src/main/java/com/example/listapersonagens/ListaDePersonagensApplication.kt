package com.example.listapersonagens

import android.app.Application
import com.example.listapersonagens.di.AppContainer

class ListaDePersonagensApplication : Application() {

    val appContainer by lazy {
        AppContainer()
    }
}
