package com.ardnn.carita.domain.main.interactor

import com.ardnn.carita.domain.main.repository.MainRepository
import io.reactivex.Observable
import javax.inject.Inject

class SaveHasBeenLaunchedUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(): Observable<Unit> =
        mainRepository.saveHasBeenLaunched()
}