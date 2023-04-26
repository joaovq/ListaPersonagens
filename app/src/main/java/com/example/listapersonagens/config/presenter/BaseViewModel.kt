package com.example.listapersonagens.config.presenter

import androidx.lifecycle.ViewModel

/**
 * View Model base para implementação de arquitetura MVVM com Model View Presenter (MVP)
 * */

abstract class BaseViewModel<out S, in T> : ViewModel(), ViewModelPresenter<S>

interface ViewModelPresenter<out S> {
    val state: S
}
