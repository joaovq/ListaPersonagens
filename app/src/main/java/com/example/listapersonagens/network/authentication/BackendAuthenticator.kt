package com.example.listapersonagens.network.authentication

import com.example.listapersonagens.model.domain.User

object BackendAuthenticator : Authenticator {

    private val registeredUsers = listOf(
        User("teste3@gmail.com", "789"),
        User("teste4@gmail.com", "123"),
    )

    override fun login(email: String, senha: String): Boolean =
        registeredUsers.any { it.email == email && it.password == senha }
}
