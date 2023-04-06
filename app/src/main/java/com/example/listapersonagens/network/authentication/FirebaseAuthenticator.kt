package com.example.listapersonagens.network.authentication

import com.example.listapersonagens.model.domain.User

object FirebaseAuthenticator : Authenticator {

    private val registeredUsers = listOf(
        User("teste1@gmail.com", "123"),
        User("teste2@gmail.com", "456"),
    )

    override fun login(email: String, senha: String): Boolean =
        registeredUsers.any { it.email == email && it.password == senha }
}
