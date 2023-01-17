package com.ardnn.carita.domain.main.interactor

import com.ardnn.carita.domain.main.repository.MainRepository
import com.ardnn.carita.data.main.repository.source.local.model.User
import io.reactivex.Observable
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(): Observable<User> =
        mainRepository.getUser()
}