package dev.jvitor.motivacao.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.jvitor.motivacao.infra.Constants
import dev.jvitor.motivacao.infra.SecurityPreferences

class UserViewModel(application: Application) : AndroidViewModel(application)  {

    private val security = SecurityPreferences(application)

    // Função responsável por guardar os nomes de usuários
    fun saveValueName(name: String) {
        security.storeString(Constants.Names.USER_NAME, name)
    }
}