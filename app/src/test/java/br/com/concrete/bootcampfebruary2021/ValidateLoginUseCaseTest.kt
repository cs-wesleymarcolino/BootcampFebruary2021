package br.com.concrete.bootcampfebruary2021

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.IllegalArgumentException

class ValidateLoginUseCaseTest {

    private val mockedPasswordValidator = mockk<PasswordValidator>()
    private val validateLoginUseCase = ValidateLoginUseCase(mockedPasswordValidator)

    @Test
    fun givenInvalidPassword_whenValidatingLogin_shouldReturnFalse() {
        every { mockedPasswordValidator.validate(any()) } returns false

        val result = validateLoginUseCase.execute("aB.3hh")

        assertFalse(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenEmptyPassword_whenValidatingLogin_shouldReturnFalse() {
        validateLoginUseCase.execute("")
    }

    @Test
    fun givenValidPassword_whenValidatingLogin_shouldReturnTrue() {
        every { mockedPasswordValidator.validate(any()) } returns true

        val result = validateLoginUseCase.execute("aA.1jk2ii")

        assertTrue(result)
    }
}