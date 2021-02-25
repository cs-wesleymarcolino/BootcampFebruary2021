package br.com.concrete.bootcampfebruary2021

class PasswordValidator {
    private val validations = listOf(
        "[a-z]",
        "[A-Z]",
        "[0-9]",
        "[\\w\\W]{8,}",
        "[\\W]"
    ).map { it.toRegex() }

    fun validate(password: String) = validations.all { validation ->
        password.contains(validation)
    }
}
