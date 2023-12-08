package dev.jvitor.motivacao.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    // Criação do "banco" para salvar e retornar valores
    private val security: SharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    // Função responsável por salvar valores
    fun storeString(key: String, value: String){
        security.edit().putString(key, value).apply()
    }

    // Função responsável por retornar valores salvos
    fun getString(key: String) : String {
        return security.getString(key, "") ?: ""
    }
}