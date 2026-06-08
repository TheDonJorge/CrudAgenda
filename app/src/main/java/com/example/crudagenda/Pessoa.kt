package com.example.crudagenda

data class Pessoa(
    var id: Int = 0, // Valor padrão 0 para quando o bando ainda não gerou ID
    var nome: String,
    var telefone: String,
    var email: String,
    var cidade: String
)
