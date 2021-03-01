package br.com.concrete.bootcampfebruary2021

import android.app.Activity
import android.app.Instrumentation
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry

class loginArrange(action: loginArrange.() -> Unit) {
    init {
        action(this)
    }

    fun intentInit() {
        Intents.init()
    }

    fun mockHomeScreenActivity() {
        intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
    }
}

class loginAct(action: loginAct.() -> Unit) {
    init {
        action(this)
    }

    fun typeEmail(email: String) {
        onView(withId(R.id.email))
            .perform(typeText(email))
    }

    fun typePassword(password: String) {
        onView(withId(R.id.password))
            .perform(typeText(password))
    }

    fun clickLogin() {
        onView(withId(R.id.login))
            .perform(ViewActions.click())
    }

    fun clickForgotPassword() {
        onView(withId(R.id.forgot_password))
            .perform(ViewActions.click())
    }
}

class loginAssert(action: loginAssert.() -> Unit) {
    init {
        action(this)
    }

    fun checkEmailFieldIsEmpty() {
        onView(withId(R.id.email))
            .check(matches(withText("")))
    }

    fun checkPasswordFieldIsEmpty() {
        onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    fun checkMessageWasShown(message: String) {
        onView(withText(message))
            .check(matches(isDisplayed()))
    }

    fun checkMessageWasShown(@StringRes messageRes: Int) {
        val message = InstrumentationRegistry
            .getInstrumentation()
            .targetContext.getString(messageRes)

        checkMessageWasShown(message)
    }

    fun checkHomeScreenWasCalled() {
        Intents.intended(hasComponent(HomeActivity::class.java.name))
    }

    fun intentRelease() {
        Intents.release()
    }
}
