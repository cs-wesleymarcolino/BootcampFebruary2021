package br.com.concrete.bootcampfebruary2021

import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordValidatorTest {
    private val passwordValidator = PasswordValidator()

    @Test
    fun givenPasswordIsShorterThan8_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.validate("aB.3hh")

        assertEquals(false, result)
    }

    @Test
    fun givenPasswordDoesNotContainUppercaseLetter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.validate("ab.23j23kl")

        assertEquals(false, result)
    }

    @Test
    fun givenPasswordDoesNotContainLowercaseLetter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.validate("AB.23J23KL")

        assertEquals(false, result)
    }

    @Test
    fun givenPasswordDoesNotContainNumber_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.validate("Ab.xxjxkl")

        assertEquals(false, result)
    }

    @Test
    fun givenPasswordDoesNotContainSpecialCharacter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.validate("Abrr23j23kl")

        assertEquals(false, result)
    }

    @Test
    fun givenPasswordIsValid_whenValidate_shouldReturnTrue() {
        val result = passwordValidator.validate("aA.1jk2ii")

        assertEquals(true, result)
    }
}