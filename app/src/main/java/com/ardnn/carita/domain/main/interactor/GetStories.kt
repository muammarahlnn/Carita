package com.ardnn.carita.domain.main.interactor

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.main.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GetStories, 18/01/2023 16.04 by Muammar Ahlan Abimanyu
 */
class GetStories @Inject constructor(
    private val mainRepository: MainRepository
) : BaseUseCase<GetStories.Params, PagingData<StoryResponse>>() {

    override fun buildUseCase(params: Params): Flow<PagingData<StoryResponse>> =
        mainRepository.getStories(params.token).cachedIn(params.coroutineScope)

    class Params(
        internal val token: String,
        internal val coroutineScope: CoroutineScope
    )
}