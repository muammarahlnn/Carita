package com.ardnn.carita.ui.signup

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.databinding.ActivitySignUpBinding
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.collectLifecycleFlow
import com.ardnn.carita.ui.util.showToast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable.combineLatest
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SignUpViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivitySignUpBinding

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLifecycleUiState()
        setupAction()
        validatingForm()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun setupInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }

    private fun initLifecycleUiState() {
        collectLifecycleFlow(viewModel.uiState) { state ->
            when (state) {
                is SignUpUiState.Loading -> {
                    if (state.isLoading) showLoading()
                    else hideLoading()
                }

                is SignUpUiState.OnSuccessRegisterUser -> {
                    showToast(this@SignUpActivity, getString(R.string.success_register))
                    finish()
                }

                is SignUpUiState.OnErrorRegisterUser -> {
                    showToast(this@SignUpActivity, state.errorMessage)
                }

                else -> {
                    // no implementation
                }
            }
        }
    }

    private fun showLoading() {
        binding.loading.root.visibility = View.VISIBLE
        enablingViews(false)
    }

    private fun hideLoading() {
        binding.loading.root.visibility = View.INVISIBLE
        enablingViews(true)
    }

    private fun enablingViews(isEnable: Boolean) {
        with(binding) {
            listOf<View>(etName, etEmail, etPassword, btnContinue, tvLogin).forEach {
                it.isEnabled = isEnable
            }
        }
    }

    private fun setupAction() {
        with(binding) {
            tvLogin.setOnClickListener {
                // back to login
                finish()
            }
            btnContinue.setOnClickListener {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val registerRequest = RegisterRequest(name, email, password)
        viewModel.postRegister(registerRequest)
    }

    private fun validatingForm() {
        val nameValidation = RxTextView.textChanges(binding.etName)
            .skipInitialValue()
            .map { name ->
                name.length < 3
            }
        // the checking to show error in editText is still in CustomView
        val passwordValidation = RxTextView.textChanges(binding.etPassword)
            .map { password ->
                password.length < 6
            }
        val formValidation = combineLatest(
            nameValidation,
            passwordValidation
        ) { nameInvalid, passwordInvalid ->
            !nameInvalid && !passwordInvalid
        }

        disposables.addAll(
            nameValidation.subscribe { isNotValid ->
                if (isNotValid) {
                    binding.etName.error = getString(R.string.name_not_valid)
                }
            },
            passwordValidation.subscribe(),
            formValidation.subscribe { isValid ->
                if (isValid) {
                    binding.btnContinue.isEnabled = true
                    binding.btnContinue.setBackgroundColor(ContextCompat.getColor(this, R.color.navy))
                } else {
                    binding.btnContinue.isEnabled = false
                    binding.btnContinue.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
            }
        )
    }
}