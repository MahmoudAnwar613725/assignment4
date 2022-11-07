package com.course.walmart.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.course.walmart.databinding.ActivityCreateNewAccountBinding
import com.course.walmart.databinding.ActivityCreateNewAccountBinding.inflate
import com.course.walmart.model.User

class CreateNewAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            var user = User(
                binding.tlfName.editText?.text.toString(),
                binding.tlLastName.editText?.text.toString(),
                binding.tlEmail.editText?.text.toString(),
                binding.tlPassword.editText?.text.toString()
            )
            createAccount(user)
        }
    }

    private fun createAccount(user: User) {
        Toast.makeText(
            this,
            "User ${user.firstName} has been created successfully",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this, LoginActivity::class.java).putExtra("user", user))
        finish()
    }

    private fun validateFields(): Boolean {
        if (binding.tlfName.editText?.text?.isEmpty() == true) {
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.tlLastName.editText?.text?.isEmpty() == true) {
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show()
            return false
        }

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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}