package com.example.listapersonagens.network.authentication

// Depois
// Criação de uma interface para a Autenticação
/** Open for implement Closed for Modification =>
 *   Utilização do conceito a partir da interface.
 *   A utilização deste principio torna o código
 *   mais fácil de manter porque basta passar a interface como dependência que
 *   vamos prover a implementação do objeto que foi passado
 * */
interface Authenticator {
    fun login(email: String, senha: String): Boolean
}
