package com.ardnn.carita.ui.signup

import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.databinding.ActivitySignUpBinding
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.showSnackbar
import com.ardnn.carita.ui.util.showToast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.Observable.*
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
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
        val formValidation = combineLatest(
            nameValidation,
            emailValidation,
            passwordValidation
        ) { nameInvalid, emailInvalid, passwordInvalid ->
            !nameInvalid && !emailInvalid && !passwordInvalid
        }

        disposables.addAll(
            nameValidation.subscribe { isNotValid ->
                if (isNotValid) {
                    binding.etName.error = getString(R.string.name_not_valid)
                }
            },
            emailValidation.subscribe { isNotValid ->
                if (isNotValid) {
                    binding.etEmail.error = getString(R.string.email_not_valid)
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