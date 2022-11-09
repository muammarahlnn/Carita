package com.ardnn.carita.data.main.interactor

import com.ardnn.carita.data.main.repository.MainRepository
import io.reactivex.Observable
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

   fun execute(): Observable<Unit> =
       mainRepository.logout()
}