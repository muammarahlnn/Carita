package com.ardnn.carita.ui.addstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.carita.domain.addstory.interactor.PostStoryUseCase
import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

class AddStoryViewModel @Inject constructor(
    private val postStoryUseCase: PostStoryUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _addStory = MutableLiveData<Status<AddStoryResponse>>()
    val addStory: LiveData<Status<AddStoryResponse>> get () = _addStory
    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ) {
        disposables.add(
            postStoryUseCase.execute("Bearer $token", file, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _addStory.value = Status.Loading()
                }
                .subscribe(
                    { addStoryResponse ->
                        _addStory.value = Status.Success(addStoryResponse)
                    },
                    {
                        _addStory.value = Status.Error(it.message.toString())
                        Timber.e(it.message.toString())
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}