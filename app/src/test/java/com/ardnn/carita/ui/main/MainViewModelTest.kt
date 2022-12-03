package com.ardnn.carita.ui.main

import com.ardnn.carita.data.main.interactor.*
import io.mockk.mockk
import org.junit.Assert.*

class MainViewModelTest {

    private val getHasBeenLaunchedUseCase = mockk<GetHasBeenLaunchedUseCase>(relaxed = true)

    private val saveHasBeenLaunchedUseCase = mockk<SaveHasBeenLaunchedUseCase>(relaxed = true)

    private val getUserUseCase = mockk<GetUserUseCase>(relaxed = true)

    private val getStoriesUseCase = mockk<GetStoriesUseCase>(relaxed = true)

    private val logoutUseCase = mockk<LogoutUseCase>(relaxed = true)

    private val mainViewModel = MainViewModel(
        getHasBeenLaunchedUseCase,
        saveHasBeenLaunchedUseCase,
        getUserUseCase,
        getStoriesUseCase,
        logoutUseCase
    )


}