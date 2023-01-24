package com.ardnn.carita.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.databinding.ActivityOnBoardingBinding
import com.ardnn.carita.ui.login.LoginActivity
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.showToast
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class OnBoardingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: OnBoardingViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initInject()

        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLifecycleActivity()
        setupAction()
    }

    private fun initInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }

    private fun setupAction() {
        binding.btnStart.setOnClickListener {
            viewModel.saveHasBeenLaunched()
        }
    }

    private fun initLifecycleActivity() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is OnBoardingUiState.ErrorToast -> {
                            showToast(this@OnBoardingActivity, getString(state.stringId))
                        }

                        is OnBoardingUiState.OnSuccessSaveHasBeenLaunched -> {
                            handleOnSuccessHasBeenLaunched()
                        }

                        else -> {
                            // no - op
                        }
                    }
                }
            }
        }
    }

    private fun handleOnSuccessHasBeenLaunched() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}