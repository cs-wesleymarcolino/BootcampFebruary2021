package br.com.concrete.bootcampfebruary2021

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(
    private val validateLoginUseCase: ValidateLoginUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(validateLoginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
