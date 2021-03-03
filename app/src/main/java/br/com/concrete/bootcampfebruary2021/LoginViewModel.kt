package br.com.concrete.bootcampfebruary2021

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalArgumentException

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase
) : ViewModel() {

    private val state = MutableLiveData<LoginViewModelState>()

    fun getViewState(): LiveData<LoginViewModelState> = state

    fun validateLogin(password: String) {
        try {
            if (validateLoginUseCase.execute(password)) {
                state.value = LoginViewModelState.Success
            } else {
                state.value = LoginViewModelState.Error(R.string.error_invalid_password)
            }
        } catch (exception: IllegalArgumentException) {
            state.value = LoginViewModelState.Error(R.string.error_empty_password)
        }
    }
}
