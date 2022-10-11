package com.ardnn.carita.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.databinding.ActivityLoginBinding
import com.ardnn.carita.ui.signup.SignUpActivity
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.showSnackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityLoginBinding

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
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

    private fun setupViewModel() {
        viewModel.snackbarMessage.observe(this) { event ->
            event.getContentIfNotHandled()?.let { message ->
                showSnackbar(this, message)
            }
        }
    }

    private fun setupAction() {
        with(binding) {
            btnLogin.setOnClickListener {
                loginUser()
            }
            tvRegister.setOnClickListener {
                // to sign up
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val loginRequest = LoginRequest(email, password)
        viewModel.postLogin(loginRequest)
    }

    private fun validatingForm() {
        val emailValidation = RxTextView.textChanges(binding.etEmail)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        // the checking to show error in editText is still in CustomView
        val passwordValidation = RxTextView.textChanges(binding.etPassword)
            .map { password ->
                password.length < 6
            }
        val formValidation = Observable.combineLatest(
            emailValidation,
            passwordValidation
        ) { emailInvalid, passwordInvalid ->
            !emailInvalid && !passwordInvalid
        }

        disposables.addAll(
            emailValidation.subscribe { isNotValid ->
                if (isNotValid) {
                    binding.etEmail.error = getString(R.string.email_not_valid)
                }
            },
            passwordValidation.subscribe(),
            formValidation.subscribe { isValid ->
                if (isValid) {
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.navy)
                    )
                } else {
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.setBackgroundColor(
                        ContextCompat.getColor(this, android.R.color.darker_gray)
                    )
                }
            }
        )
    }
}