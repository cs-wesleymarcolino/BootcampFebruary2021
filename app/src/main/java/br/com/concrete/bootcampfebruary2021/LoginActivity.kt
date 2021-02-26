package br.com.concrete.bootcampfebruary2021

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

        val login = findViewById<View>(R.id.login)
        val email = findViewById<TextView>(R.id.email)
        val password = findViewById<TextView>(R.id.password)
        val forgotPassword = findViewById<TextView>(R.id.forgot_password)

        login.setOnClickListener {
            when {
                email.text.isEmpty() -> showMessage(R.string.error_empty_email)
                password.text.isEmpty() -> showMessage(R.string.error_empty_password)
                !passwordValidator.validate(password.text.toString()) -> showMessage(R.string.error_invalid_password)
                else -> {

                }
            }
        }
        forgotPassword.setOnClickListener {
            if (email.text.isEmpty()) {
                showMessage(R.string.error_email_required)
            } else {

                // TODO implement email sent
            }
        }
    }

    private fun showMessage(@StringRes messageRes: Int) {
        AlertDialog.Builder(this)
            .setMessage(messageRes)
            .show()
    }
}