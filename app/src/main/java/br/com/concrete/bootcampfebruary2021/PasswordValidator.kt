package br.com.concrete.bootcampfebruary2021

class PasswordValidator {
    fun validate(password: String): Boolean {
        return password.length >= 8
    }
}
