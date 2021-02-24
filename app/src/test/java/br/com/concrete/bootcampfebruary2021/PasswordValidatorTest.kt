package br.com.concrete.bootcampfebruary2021

import org.junit.Assert
import org.junit.Test

class PasswordValidatorTest {
    @Test
    fun givenPasswordIsShorterThan8_whenValidate_shouldReturnFalse(){
        // Arrange
        val password = "1234567"

        // Act
        val result = PasswordValidator().validate(password)

        // Assert
        Assert.assertEquals(false, result)
    }
}