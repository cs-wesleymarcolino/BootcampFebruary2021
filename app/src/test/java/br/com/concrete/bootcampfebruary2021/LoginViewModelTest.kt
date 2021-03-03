package br.com.concrete.bootcampfebruary2021

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalArgumentException

class LoginViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockedValidateLoginUseCase = mockk<ValidateLoginUseCase>()
    private val loginViewModel = LoginViewModel(mockedValidateLoginUseCase)

    @Test
    fun givenInvalidPassword_whenValidatingLogin_shouldEmitPasswordInvalidError() {
        every { mockedValidateLoginUseCase.execute(any()) } returns false

        loginViewModel.validateLogin("aB.3hh")

        assertEquals(
            loginViewModel.getViewState().value,
            LoginViewModelState.Error(R.string.error_invalid_password)
        )
    }

    @Test
    fun givenEmptyPassword_whenValidatingLogin_shouldEmitPasswordEmptyError() {
        every { mockedValidateLoginUseCase.execute(any()) } throws IllegalArgumentException()

        loginViewModel.validateLogin("")

        assertEquals(
            loginViewModel.getViewState().value,
            LoginViewModelState.Error(R.string.error_empty_password)
        )
    }

    @Test
    fun givenValidPassword_whenValidatingLogin_shouldEmitSuccess() {
        every { mockedValidateLoginUseCase.execute(any()) } returns true

        loginViewModel.validateLogin("aA.1jk2ii")

        assertEquals(
            loginViewModel.getViewState().value,
            LoginViewModelState.Success
        )
    }
}