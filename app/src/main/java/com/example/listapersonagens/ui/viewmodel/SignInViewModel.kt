package com.example.listapersonagens.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.listapersonagens.designpatterns.behavioral.LoginUiState
import com.example.listapersonagens.di.usecase.SignInUseCase
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val loginUiState = LoginUiState()
    val isLoading: StateFlow<Boolean?> = loginUiState._isLoading
    val auth: LiveData<LoginUiState.LoginState> = loginUiState._auth

    fun signIn(email: String, senha: String) {
        loginUiState._isLoading.value = true
        if (signInUseCase(email, senha)) {
            loginUiState._auth.postValue(LoginUiState.LoginState.Success)
            loginUiState._isLoading.value = false
            return
        }
        loginUiState._auth.postValue(LoginUiState.LoginState.Error)
        loginUiState._isLoading.value = false
    }
}
