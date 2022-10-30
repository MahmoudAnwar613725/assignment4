package com.course.walmart

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.course.walmart.databinding.ActivityLoginBinding
import com.course.walmart.databinding.ActivityLoginBinding.inflate
import com.course.walmart.model.User

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var list = arrayListOf(
        User("Mahmoud", "Mohamed", "moud@miu.edu", "123"),
        User("Mahmoud", "Anwar", "anwar@miu.edu", "rrr"),
        User("Judy", "ElSherif", "judy@gmail.com", "333")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvForgetPassword.setOnClickListener {
            if (binding.tlEmail.editText?.text?.isEmpty() == true) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendEmail(binding.tvEmail.text.toString())
        }

        binding.btnSignIn.setOnClickListener {
            if (!validateField())
                return@setOnClickListener
            signIn(
                binding.tlEmail.editText?.text.toString(),
                binding.tlPassword.editText?.text.toString()
            )
        }

        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateNewAccount::class.java))
            finish()
        }
    }

    private fun signIn(email: String, password: String) {
        if (checkIfUserExist(email, password)) {
            startActivity(Intent(this, ShoppingCategory::class.java).putExtra("name", email))
            finish()
        } else {
            Toast.makeText(
                this,
                "$email is not exist, click on create account to register new user",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkIfUserExist(email: String, password: String): Boolean {
        var result: Boolean =
            list.any {
                it.userName.equals(
                    email,
                    true
                ) && it.password.equals(password)
            }
        return result
    }

    private fun validateField(): Boolean {
        if (binding.tlEmail.editText?.text?.isEmpty() == true) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.tlPassword.editText?.text?.isEmpty() == true) {

            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
        intent.putExtra(
            Intent.EXTRA_TEXT, "Forgot Password for walmart app, " +
                    "for resetting contact Administrator or use this code 10001."
        );
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}