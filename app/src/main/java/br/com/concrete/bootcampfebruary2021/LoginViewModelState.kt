package br.com.concrete.bootcampfebruary2021

import androidx.annotation.StringRes

sealed class LoginViewModelState {
    data class Error(@StringRes val errorMessage: Int) : LoginViewModelState()
    object Success : LoginViewModelState()
}
