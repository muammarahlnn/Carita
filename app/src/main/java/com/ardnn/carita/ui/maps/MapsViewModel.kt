package com.ardnn.carita.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.data.maps.interactor.GetStoriesWithLocationUseCase
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val getStoriesWithLocationUseCase: GetStoriesWithLocationUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _stories = MutableLiveData<List<StoryResponse>>()
    val stories: LiveData<List<StoryResponse>> get() = _stories
    fun getStories(token: String) {
        disposables.add(
            getStoriesWithLocationUseCase.execute("Bearer $token")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { storiesResponse ->
                        storiesResponse.listStory?.let {
                            _stories.value = it
                        }
                    },
                    {
                        _stories.value = listOf()
                        Timber.e(it.localizedMessage)
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}