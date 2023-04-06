package com.example.listapersonagens.di.usecase

import com.example.listapersonagens.network.authentication.Authenticator

/** DEPOIS
 * Utilização do use case para implementar a interace o Principio Aberto-Fechado
 * */

class SignInUseCase(private val authenticator: Authenticator) {
    operator fun invoke(email: String, password: String): Boolean {
        return authenticator.login(email, password)
    }
}
