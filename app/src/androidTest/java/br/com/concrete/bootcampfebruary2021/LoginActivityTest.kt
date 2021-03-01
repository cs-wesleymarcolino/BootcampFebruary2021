package br.com.concrete.bootcampfebruary2021

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun givenInitialState_shouldHaveEmptyEmailAndPasswordFields() {
        loginAssert {
            checkEmailFieldIsEmpty()
            checkPasswordFieldIsEmpty()
        }
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmptyEmailError() {
        loginAct {
            typePassword("aA.bksj32")
            clickLogin()
        }

        loginAssert {
            checkMessageWasShown(R.string.error_empty_email)
        }
    }

    @Test
    fun givenPasswordIsEmpty_whenLogin_shouldShowEmptyPasswordError() {
        loginAct {
            typeEmail("wesley.marcolino@concrete.com.br")
            clickLogin()
        }

        loginAssert {
            checkMessageWasShown(R.string.error_empty_password)
        }
    }

    @Test
    fun givenInvalidPassword_whenLogin_shouldShowInvalidPasswordError() {
        loginAct {
            typeEmail("wesley.marcolino@concrete.com.br")
            typePassword("1234")

            clickLogin()
        }

        loginAssert {
            checkMessageWasShown(R.string.error_invalid_password)
        }
    }

    @Test
    fun givenUserHasEmailTypedAndForgotPassword_whenClick_shouldShowRecoverySent() {
        loginAct {
            typeEmail("wesley.marcolino@concrete.com.br")
            clickForgotPassword()
        }

        loginAssert {
            checkMessageWasShown("Email sent to wesley.marcolino@concrete.com.br")
        }
    }

    @Test
    fun givenUserDoesntHasEmailTypedAndForgotPassword_whenClick_shouldShowError() {
        loginAct {
            clickForgotPassword()
        }

        loginAssert {
            checkMessageWasShown(R.string.error_email_required)
        }
    }

    @Test
    fun givenValidPasswordAndEmail_whenLogin_shouldGoToHomeScreen() {
        loginArrange {
            intentInit()
            mockHomeScreenActivity()
        }

        loginAct {
            typeEmail("wesley.marcolino@concrete.com.br")
            typePassword("aA.bksj32")

            clickLogin()
        }

        loginAssert {
            checkHomeScreenWasCalled()
            intentRelease()
        }
    }
}