package com.ardnn.carita.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResult
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val postLoginUseCase = mockk<PostLoginUseCase>(relaxed = true)

    private val saveUserUseCase = mockk<SaveUserUseCase>(relaxed = true)

    private val viewModel = LoginViewModel(
        postLoginUseCase,
        saveUserUseCase
    )

    private val dummyLoginResponse = LoginResponse(
        false,
        "",
        LoginResult(
            "", "", ""
        )
    )

    private val dummyLoginRequest = LoginRequest("", "")

    private val dummyUser = User("", "", "")

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `postLogin should execute postLoginUseCase`() {
        // given
        every {
            postLoginUseCase.execute(any())
        } returns Observable.just(dummyLoginResponse)

        // when
        viewModel.postLogin(dummyLoginRequest)

        // then
        verify {
            postLoginUseCase.execute(any())
        }
    }

    @Test
    fun `postLogin should set value to response`() {
        // given
        every {
            postLoginUseCase.execute(any())
        } returns Observable.just(dummyLoginResponse)

        // when
        viewModel.postLogin(dummyLoginRequest)
        val responseData = viewModel.response.getOrAwaitValue()

        // then
        verify {
            postLoginUseCase.execute(any())
        }
        assertNotNull(responseData)
    }

    @Test
    fun `saveUser should execute saveUserUseCase`() {
        // given
        every {
            saveUserUseCase.execute(any())
        } returns Observable.just(Unit)

        // when
        viewModel.saveUser(dummyUser)

        // then
        verify {
            saveUserUseCase.execute(any())
        }
    }

    @Test
    fun `saveUser should set value to isUserSuccessfullySaved`() {
        // given
        every {
            saveUserUseCase.execute(any())
        } returns Observable.just(Unit)

        // when
        viewModel.saveUser(dummyUser)
        val isUserSuccessfullySavedData = viewModel.isUserSuccessfullySaved.getOrAwaitValue()

        // then
        verify {
            saveUserUseCase.execute(any())
        }
        assertNotNull(isUserSuccessfullySavedData)
    }
}