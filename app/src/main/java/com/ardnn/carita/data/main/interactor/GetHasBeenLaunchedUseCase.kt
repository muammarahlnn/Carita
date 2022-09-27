package com.ardnn.carita.data.main.interactor

import com.ardnn.carita.data.main.repository.MainRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetHasBeenLaunchedUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(): Observable<Boolean> =
        mainRepository.getHasBeenLaunched()
}