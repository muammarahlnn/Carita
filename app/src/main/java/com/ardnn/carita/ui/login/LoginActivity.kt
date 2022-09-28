package com.ardnn.carita.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardnn.carita.databinding.ActivityLoginBinding
import com.ardnn.carita.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.tvRegister.setOnClickListener {
            // to sign up
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}