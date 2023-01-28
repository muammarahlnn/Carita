package com.ardnn.carita.data.util

import com.ardnn.carita.data.main.repository.source.local.MainPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AuthorizationInterceptor, 28/01/2023 13.34 by Muammar Ahlan Abimanyu
 */
class AuthorizationInterceptor(
    private val mainPreferences: MainPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            mainPreferences.getUser().first().token
        }
        val request: Request = chain.request().newBuilder()
            .header(AUTHORIZATION, "Bearer $token")
            .build()
        return chain.proceed(request)
    }

    companion object {

        private const val AUTHORIZATION = "Authorization"
    }
}