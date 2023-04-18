package com.example.listapersonagens

import android.app.Application
import com.example.listapersonagens.di.modules.appModule
import com.example.listapersonagens.di.modules.domainModule
import com.example.listapersonagens.di.modules.networkModule
import com.example.listapersonagens.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ListaDePersonagensApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ListaDePersonagensApplication)
            modules(appModule, networkModule, domainModule, viewModelModule)
        }
    }
}
