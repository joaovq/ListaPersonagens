package com.example.listapersonagens.network.authentication

interface Authenticator {
    fun login(email: String, senha: String): Boolean
}
