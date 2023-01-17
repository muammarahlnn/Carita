package com.ardnn.carita.domain.login.interactor

import com.ardnn.carita.domain.login.repository.LoginRepository
import com.ardnn.carita.data.main.repository.source.local.model.User
import io.reactivex.Observable
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    fun execute(user: User): Observable<Unit> =
        loginRepository.saveUser(user)
}