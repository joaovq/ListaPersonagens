package com.example.listapersonagens.config.presenter

interface BaseFragment<T> {
    var view: T?
    fun attachView(view: T)
    fun dettachView()
}
