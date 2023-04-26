package com.example.listapersonagens.designpatterns.behavioral

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

/**
*
 * O padrão State permite alterar o comportamento de um objeto quando seu estado interno muda
 *
 * Temos como vantagens:
 *
 * - Separação de responsabilidades e manutenção
 * - Adicionar novas features sem precisar mudar códigos já existentes
 * - Simplifica os códigos de contexto por evitar códigos de máquina pesada
 *
* */

/**
* Podemos criar estados para as telas do android, quando nos comunicamos com uma autenticados
 * como o firebase, ou até um banco de dados.
 * Como por exemplo podemos aplicar para o Login
* */

class LoginUiState {

    val _isLoading: MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val _auth: MutableLiveData<LoginState> = MutableLiveData()

    sealed interface LoginState {
        object Success : LoginState
        object Error : LoginState
    }
}
