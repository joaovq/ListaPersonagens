package com.example.listapersonagens.ui.presenter

import com.example.listapersonagens.model.domain.Character
import java.lang.Exception

interface CharactersView {
    fun onLoading()
    fun onSuccess(characters: List<Character>)
    fun onFailure(exception: Exception, message: String)
    fun onHideLoading()
}
