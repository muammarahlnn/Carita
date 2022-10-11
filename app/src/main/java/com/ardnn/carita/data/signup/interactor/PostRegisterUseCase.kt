package com.ardnn.carita.data.signup.interactor

import com.ardnn.carita.data.signup.repository.SignUpRepository
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {

    fun execute(request: RegisterRequest): Observable<RegisterResponse> =
        signUpRepository.postRegister(request)
}