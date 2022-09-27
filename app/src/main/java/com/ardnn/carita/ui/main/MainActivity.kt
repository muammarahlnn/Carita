package com.ardnn.carita.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.ui.onboarding.OnBoardingActivity
import com.ardnn.carita.ui.util.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }

    private fun setupViewModel() {
        viewModel.getHasBeenLaunched()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.hasBeenLaunched.observe(this) { hasBeenLaunched ->
            Timber.d("Has been launched -> $hasBeenLaunched")
            if (!hasBeenLaunched) {
                viewModel.saveHasBeenLaunched()
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }
        }
    }
}