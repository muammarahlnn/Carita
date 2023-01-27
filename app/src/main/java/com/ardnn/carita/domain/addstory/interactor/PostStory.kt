package com.ardnn.carita.domain.addstory.interactor

import com.ardnn.carita.data.addstory.repository.source.remote.response.AddStoryResponse
import com.ardnn.carita.domain.addstory.repository.AddStoryRepository
import com.ardnn.carita.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file PostStory, 27/01/2023 21.05 by Muammar Ahlan Abimanyu
 */
class PostStory @Inject constructor(
    private val addStoryRepository: AddStoryRepository
) : BaseUseCase<PostStory.Params, AddStoryResponse>(){

    override fun buildUseCase(params: Params): Flow<AddStoryResponse> {
        return addStoryRepository.postStory(
            params.token,
            params.file,
            params.description
        )
    }

    class Params(
        internal val token: String,
        internal val file: MultipartBody.Part,
        internal val description: RequestBody
    )
}