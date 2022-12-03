package com.ardnn.carita.data.main.repository.source.remote

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.data.login.repository.source.remote.request.LoginRequest
import com.ardnn.carita.data.login.repository.source.remote.response.LoginResponse
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.signup.repository.source.remote.request.RegisterRequest
import com.ardnn.carita.data.signup.repository.source.remote.response.RegisterResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StoryDicodingApi {

    @POST(REGISTER)
    fun postRegister(
        @Body request: RegisterRequest
    ): Observable<RegisterResponse>

    @POST(LOGIN)
    fun postLogin(
        @Body request: LoginRequest
    ): Observable<LoginResponse>

    @GET(STORIES)
    fun getStories(
        @Header(AUTHORIZATION) token: String,
        @Query(PAGE) page: Int,
        @Query(SIZE) size: Int,
        @Query(LOCATION) location: Int = 0
    ): Single<StoriesResponse>

    @Multipart
    @POST(STORIES)
    fun postStory(
        @Header(AUTHORIZATION) token: String,
        @Part file: MultipartBody.Part,
        @Part(DESCRIPTION) description: RequestBody
    ): Observable<AddStoryResponse>

    companion object {

        private const val REGISTER = "v1/register"
        private const val LOGIN = "v1/login"
        private const val STORIES = "v1/stories"
        private const val AUTHORIZATION = "Authorization"
        private const val DESCRIPTION = "description"
        private const val PAGE = "page"
        private const val SIZE = "size"
        private const val LOCATION = "location"
    }
}