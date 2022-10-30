package com.course.walmart

import android.annotation.SuppressLint
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
    var usersList = arrayListOf(
        User("Mahmoud", "Mohamed", "moud@miu.edu", "123"),
        User("Mahmoud", "Anwar", "anwar@miu.edu", "rrr"),
        User("Judy", "ElSherif", "judy@gmail.com", "333")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //add user if it comes from create account activity and login directly
        addNewUser()
        binding.tvForgetPassword.setOnClickListener {
            if (binding.tlEmail.editText?.text?.isEmpty() == true) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendEmail(binding.tlEmail.editText?.text.toString())
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

    /**
     * check if user extra has data this means it comes from create account
     * add user to memeory database
     * finish activity and sign in automatically
     */
    private fun addNewUser() {
        val user: User? = intent.getSerializableExtra("user") as User?
        user?.let {
            usersList.add(it)
            signIn(user.userName,user.password)
        }
    }

    private fun signIn(email: String, password: String) {
        if (checkIfUserExist(email, password)) {
            startActivity(Intent(this, MainActivity::class.java).putExtra("name", email))
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
              usersList.any {
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


    @SuppressLint("QueryPermissionsNeeded")
    private fun sendEmail(email: String) {
        var pass: String = getPassword(email)
        if (getPassword(email).isEmpty()) {
            Toast.makeText(this, "this email is not registered on ourdatabase", Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
            intent.putExtra(
                Intent.EXTRA_TEXT, "Forgot Password for walmart app, " +
                        "your password is $pass"
            )
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

    }

    private fun getPassword(email: String): String {
        val s = usersList.find { it.userName.equals(email, true) }
        return s?.password ?: ""
    }

    /*  @SuppressLint("QueryPermissionsNeeded")
      fun onForgetPasswordClick(view: View) {
          val email = binding.tlEmail.editText?.text.toString().trim()
          val intent = Intent(Intent.ACTION_SENDTO)
          intent.data = Uri.parse("mailto:")
          intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email))
          intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
          intent.putExtra(Intent.EXTRA_TEXT, "message")

          if (intent.resolveActivity(packageManager) != null) {
              startActivity(intent)
          }
          try {
              //start email intent
              startActivity(Intent.createChooser(intent, "Choose Email Client..."))
          }
          catch (e: Exception){
              //if any thing goes wrong for example no email client application or any exception
              //get and show exception message
              Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
          }

      }*/
}