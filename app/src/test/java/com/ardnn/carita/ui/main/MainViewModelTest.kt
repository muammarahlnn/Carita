package com.ardnn.carita.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.ardnn.carita.data.main.interactor.*
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getHasBeenLaunchedUseCase = mockk<GetHasBeenLaunchedUseCase>(relaxed = true)

    private val saveHasBeenLaunchedUseCase = mockk<SaveHasBeenLaunchedUseCase>(relaxed = true)

    private val getUserUseCase = mockk<GetUserUseCase>(relaxed = true)

    private val getStoriesUseCase = mockk<GetStoriesUseCase>(relaxed = true)

    private val logoutUseCase = mockk<LogoutUseCase>(relaxed = true)

    private val viewModel = MainViewModel(
        getHasBeenLaunchedUseCase,
        saveHasBeenLaunchedUseCase,
        getUserUseCase,
        getStoriesUseCase,
        logoutUseCase
    )

    private val dummyUser: User by lazy {
        User("", "", "")
    }

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `getHasBeenLaunched should execute getHasBeenLaunchedUseCase`() {
        // given
        every {
            getHasBeenLaunchedUseCase.execute()
        } returns Observable.just(false)

        // when
        viewModel.getHasBeenLaunched()

        // then
        verify {
            getHasBeenLaunchedUseCase.execute()
        }
    }

    @Test
    fun `getHasBeenLaunched should set value to hasBeenLaunched`() {
        // given
        every {
            getHasBeenLaunchedUseCase.execute()
        } returns Observable.just(false)

        // when
        viewModel.getHasBeenLaunched()
        viewModel.hasBeenLaunched.getOrAwaitValue()

        // then
        verify {
            getHasBeenLaunchedUseCase.execute()
        }
        assertNotNull(viewModel.hasBeenLaunched)
    }

    @Test
    fun `saveHasBeenLaunched should execute saveHasBeenLaunchedUseCase`() {
        // given
        every {
            saveHasBeenLaunchedUseCase.execute()
        } returns Observable.just(Unit)

        // when
        viewModel.saveHasBeenLaunched()

        // then
        every {
            saveHasBeenLaunchedUseCase.execute()
        }
    }

    @Test
    fun `getUser should execute getUserUseCase`() {
        // given
        every {
            getUserUseCase.execute()
        } returns Observable.just(dummyUser)

        // when
        viewModel.getUser()

        // then
        verify {
            getUserUseCase.execute()
        }
    }

    @Test
    fun `getUser should set value to user`() {
        // given
        every {
            getUserUseCase.execute()
        } returns Observable.just(dummyUser)

        // when
        viewModel.getUser()
        viewModel.user.getOrAwaitValue()

        // then
        verify {
            getUserUseCase.execute()
        }
        assertNotNull(viewModel.user)
    }

    @Test
    fun `getUser should set value to isLogin`() {
        // given
        every {
            getUserUseCase.execute()
        } returns Observable.just(dummyUser)

        // when
        viewModel.getUser()
        viewModel.isLogin.getOrAwaitValue()

        // then
        verify {
            getUserUseCase.execute()
        }
        assertNotNull(viewModel.isLogin)
    }

    @Test
    fun `getStories should execute getStoriesUseCase`() {
        // given
        every {
            getStoriesUseCase.execute(any())
        } returns Observable.just(PagingData.empty())

        // when
        viewModel.getStories("")

        // then
        verify {
            getStoriesUseCase.execute(any())
        }
    }

    @Test
    fun `getStories should set value to stories`() {
        // given
        every {
            getStoriesUseCase.execute(any())
        } returns Observable.just(PagingData.empty())

        // when
        viewModel.getStories("")
        viewModel.stories.getOrAwaitValue()

        // then
        verify {
            getStoriesUseCase.execute(any())
        }
        assertNotNull(viewModel.stories)
    }

    @Test
    fun `logout should execute logoutUseCase`() {
        // given
        every {
            logoutUseCase.execute()
        } returns Observable.just(Unit)

        // when
        viewModel.logout()

        // then
        verify {
            logoutUseCase.execute()
        }
    }

    @Test
    fun `logout should set value to logoutStatus`() {
        // given
        every {
            logoutUseCase.execute()
        } returns Observable.just(Unit)

        // when
        viewModel.logout()
        viewModel.logoutStatus.getOrAwaitValue()

        // then
        verify {
            logoutUseCase.execute()
        }
        assertNotNull(viewModel.logoutStatus)
    }
}