package br.com.concrete.bootcampfebruary2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    private val passwordValidator = PasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginView = findViewById<View>(R.id.login)
        val emailView = findViewById<TextView>(R.id.email)
        val passwordView = findViewById<TextView>(R.id.password)
        val forgotPasswordView = findViewById<TextView>(R.id.forgot_password)

        loginView.setOnClickListener {
            when {
                emailView.text.isEmpty() -> showMessage(R.string.error_empty_email)
                passwordView.text.isEmpty() -> showMessage(R.string.error_empty_password)
                !passwordValidator.validate(passwordView.text.toString()) -> showMessage(R.string.error_invalid_password)
                else -> {
                    val homeScreenIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(homeScreenIntent)
                }
            }
        }
        forgotPasswordView.setOnClickListener {
            val email = emailView.text
            if (email.isEmpty()) {
                showMessage(R.string.error_email_required)
            } else {
                showMessage(getString(R.string.email_sent, email))
            }
        }
    }

    private fun showMessage(@StringRes messageRes: Int) {
        showMessage(getString(messageRes))
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .show()
    }
}