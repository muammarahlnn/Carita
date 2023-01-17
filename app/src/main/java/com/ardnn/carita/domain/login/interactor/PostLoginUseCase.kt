package com.ardnn.carita.domain.login.interactor

import com.ardnn.carita.domain.login.repository.LoginRepository
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import io.reactivex.Observable
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    fun execute(request: LoginRequest): Observable<LoginResponse> =
        loginRepository.postLogin(request)
}