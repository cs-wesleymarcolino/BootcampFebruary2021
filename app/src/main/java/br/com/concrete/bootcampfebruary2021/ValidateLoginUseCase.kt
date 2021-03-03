package br.com.concrete.bootcampfebruary2021

import java.lang.IllegalArgumentException

class ValidateLoginUseCase(
    private val passwordValidator: PasswordValidator
) {

    fun execute(password: String): Boolean {
        if (password.isEmpty()) throw IllegalArgumentException()
        return passwordValidator.validate(password)
    }
}
