package com.ardnn.carita.ui.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
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

class SignUpViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val postRegisterUseCase = mockk<PostRegisterUseCase>(relaxed = true)

    private val viewModel = SignUpViewModel(postRegisterUseCase)

    private val dummyRegisterResponse = RegisterResponse(false, "")

    private val dummyRegisterRequest = RegisterRequest("", "", "")

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        every {
            postRegisterUseCase.execute(any())
        } returns Observable.just(dummyRegisterResponse)
    }

    @Test
    fun `postRegister should execute postRegisterUseCase`() {
        // when
        viewModel.postRegister(dummyRegisterRequest)

        // then
        verify {
            postRegisterUseCase.execute(any())
        }
    }

    @Test
    fun `postRegister should set value to message`() {
        // when
        viewModel.postRegister(dummyRegisterRequest)
        val messageData = viewModel.message.getOrAwaitValue()

        // then
        verify {
            postRegisterUseCase.execute(any())
        }
        assertNotNull(messageData)
    }

    @Test
    fun `postRegister should set value to response`() {
        // when
        viewModel.postRegister(dummyRegisterRequest)
        val responseData = viewModel.response.getOrAwaitValue()

        // then
        verify {
            postRegisterUseCase.execute(any())
        }
        assertNotNull(responseData)
    }
}