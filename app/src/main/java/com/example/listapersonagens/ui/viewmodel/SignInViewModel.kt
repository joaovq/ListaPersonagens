package com.example.listapersonagens.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listapersonagens.di.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val _isAuth: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isAuth: StateFlow<Boolean?> get() = _isAuth

    fun signIn(email: String, senha: String) {
        _isAuth.value = signInUseCase(email, senha)
    }
}
