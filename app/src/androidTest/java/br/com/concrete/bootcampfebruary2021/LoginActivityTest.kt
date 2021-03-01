package br.com.concrete.bootcampfebruary2021

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.email))
            .check(matches(withText("")))

        onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmptyEmailError() {
        onView(withId(R.id.password))
            .perform(typeText("aA.bksj32"))
        onView(withId(R.id.login))
            .perform(click())

        onView(withText(R.string.error_empty_email))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordIsEmpty_whenLogin_shouldShowEmptyPasswordError() {
        onView(withId(R.id.email))
            .perform(typeText("wesley.marcolino@concrete.com.br"))
        onView(withId(R.id.login))
            .perform(click())

        onView(withText(R.string.error_empty_password))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInvalidPassword_whenLogin_shouldShowInvalidPasswordError() {
        onView(withId(R.id.email))
            .perform(typeText("wesley.marcolino@concrete.com.br"))
        onView(withId(R.id.password))
            .perform(typeText("1234"))
        onView(withId(R.id.login))
            .perform(click())

        onView(withText(R.string.error_invalid_password))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenUserHasEmailTypedAndForgotPassword_whenClick_shouldShowRecoverySent() {
        onView(withId(R.id.email))
            .perform(typeText("wesley.marcolino@concrete.com.br"))
        onView(withId(R.id.forgot_password))
            .perform(click())

        onView(withText("Email sent to wesley.marcolino@concrete.com.br"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenUserDoesntHasEmailTypedAndForgotPassword_whenClick_shouldShowError() {
        onView(withId(R.id.forgot_password))
            .perform(click())

        onView(withText(R.string.error_email_required))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenValidPasswordAndEmail_whenLogin_shouldGoToHomeScreen() {
        Intents.init()
        intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))

        onView(withId(R.id.email))
            .perform(typeText("wesley.marcolino@concrete.com.br"))
        onView(withId(R.id.password))
            .perform(typeText("aA.bksj32"))
        onView(withId(R.id.login))
            .perform(click())

        intended(hasComponent(HomeActivity::class.java.name))
        Intents.release()
    }
}