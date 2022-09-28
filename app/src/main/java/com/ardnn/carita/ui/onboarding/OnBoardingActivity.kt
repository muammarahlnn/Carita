package com.ardnn.carita.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardnn.carita.databinding.ActivityOnBoardingBinding
import com.ardnn.carita.ui.login.LoginActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.btnStart.setOnClickListener {
            // to login page
            val toLogin = Intent(this, LoginActivity::class.java)
            startActivity(toLogin)
            finish()
        }
    }
}