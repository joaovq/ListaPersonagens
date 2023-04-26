package com.example.listapersonagens.designpatterns.structurals

import android.util.Patterns
import androidx.core.text.isDigitsOnly

/**
 * Através do decorator, podemos adicionar novos comportamentos além do compotamento básico da classe
 * sem a necessidade de modificar o código existente
 *
 * Vantagens
 *
 * - Possibilidade de extendes um comportamento sem uma subclasse
 * - Adicionar ou remover responsabilidades em tempo de execução
 * - Multiplicar comportamentos com multiplos decoradores
 * - Principio da responsabilidade única: divisão de uma classe monolítica que implementa muitas
 * possíveis variantes de um comportamento em diversas classes menores.
 * */

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).find()
/**
 *
 * Valida o password e verifica se ele tem somente digitos
 * */
fun String.validatePassword() = this.isDigitsOnly()
