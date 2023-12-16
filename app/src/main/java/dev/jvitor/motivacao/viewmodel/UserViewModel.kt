package dev.jvitor.motivacao.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.jvitor.motivacao.infra.Constants
import dev.jvitor.motivacao.infra.SecurityPreferences
import dev.jvitor.motivacao.infra.ValidateName

class UserViewModel(application: Application) : AndroidViewModel(application)  {

    private val security = SecurityPreferences(application)
    private val validateName = ValidateName()

    // Função responsável por guardar os nomes de usuários
    fun saveValueName(name: String) {
        security.storeString(Constants.Names.USER_NAME, name)
    }

    // Função responsável por verificar o nome que o usuário inseriu
    fun verifyName(userName: String) : Boolean  {
        return validateName.fieldNameIsNotEmpty(userName)
    }
}