package com.ardnn.carita.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardnn.carita.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.tvLogin.setOnClickListener {
            // back to login
            finish()
        }
    }
}