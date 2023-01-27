package com.ardnn.carita.ui.addstory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

class AddStoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val postStoryUseCase = mockk<PostStoryUseCase>(relaxed = true)

    private val viewModel = AddStoryViewModel(postStoryUseCase)

    private val file = mockk<File>(relaxed = true)

    private val multipartBody = mockk<MultipartBody.Part>(relaxed = true)

    private val requestBody = mockk<RequestBody>(relaxed = true)

    private val dummyAddStoryResponse: AddStoryResponse by lazy {
        AddStoryResponse(false, "")
    }

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        every {
            postStoryUseCase.execute(any(), any(), any())
        } returns Observable.just(dummyAddStoryResponse)
    }

    @Test
    fun `postStory should execute postStoryUseCase`() {
        // when
        viewModel.postStory("", multipartBody, requestBody)

        // then
        verify {
            postStoryUseCase.execute(any(), any(), any())
        }
    }

    @Test
    fun `postStory should set value to addStory`() {
        // when
        viewModel.postStory("", multipartBody, requestBody)
        val addStoryData = viewModel.addStory.getOrAwaitValue()

        // then
        verify {
            postStoryUseCase.execute(any(), any(), any())
        }
        assertNotNull(addStoryData)
    }
}