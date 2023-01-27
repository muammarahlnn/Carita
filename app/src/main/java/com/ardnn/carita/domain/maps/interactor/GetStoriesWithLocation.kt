package com.ardnn.carita.domain.maps.interactor

import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.domain.base.BaseUseCase
import com.ardnn.carita.domain.maps.repository.MapsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GetStoriesWithLocation, 27/01/2023 21.51 by Muammar Ahlan Abimanyu
 */
class GetStoriesWithLocation @Inject constructor(
    private val mapsRepository: MapsRepository
) : BaseUseCase<GetStoriesWithLocation.Params, StoriesResponse>(){

    override fun buildUseCase(params: Params): Flow<StoriesResponse> {
        return mapsRepository.getStories(params.token)
    }

    class Params(
        internal val token: String
    )
}