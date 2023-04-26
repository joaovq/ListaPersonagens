package com.example.listapersonagens.designpatterns.creationals

import android.app.Application
import retrofit2.Retrofit

/**
Garante uma instância única durante a execução da aplicação
Singleton vantagens:
- inicialização somente quando for iniciado a aplicação
- Ponto de acesso global, na aplicação
 */

object RetrofitConfig {
    private var instance: Retrofit? = null
    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder().baseUrl("http://www.url-qualquer.com.br").build()
        }
        return instance as Retrofit
    }
}
/**
Um exemplo que foi utlizado na aplicação é o exemplo da biblioteca retrofit
que também poderiamos criar uma instância atraves da classe abaixo
 */

/*O ponto de acesso global*/

class AppContainer {
    val retrofit = RetrofitConfig.getInstance()
}

class MyApp : Application() {
    val appContainer = AppContainer()
}
