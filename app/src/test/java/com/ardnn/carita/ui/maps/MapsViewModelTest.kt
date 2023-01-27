package com.ardnn.carita.ui.maps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
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

class MapsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getStoriesWithLocationUseCase = mockk<GetStoriesWithLocationUseCase>()

    private val viewModel = MapsViewModel(getStoriesWithLocationUseCase)

    private val dummyStoriesResponse = StoriesResponse(
        false,
        "",
        listOf()
    )

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        every {
            getStoriesWithLocationUseCase.execute(any())
        } returns Observable.just(dummyStoriesResponse)
    }

    @Test
    fun `getStories should execute getStoriesWithLocationUseCase`() {
        // when
        viewModel.getStories("")

        // then
        verify {
            getStoriesWithLocationUseCase.execute(any())
        }
    }

    @Test
    fun `getStories should set value to stories`() {
        // when
        viewModel.getStories("")
        val storiesData = viewModel.stories.getOrAwaitValue()

        // then
        verify {
            getStoriesWithLocationUseCase.execute(any())
        }
        assertNotNull(storiesData)
    }
}