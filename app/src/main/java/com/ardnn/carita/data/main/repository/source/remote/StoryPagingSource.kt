package com.ardnn.carita.data.main.repository.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file StoryPagingSource, 18/01/2023 14.39 by Muammar Ahlan Abimanyu
 */
class StoryPagingSource(
    private val api: StoryDicodingApi,
    private val token: String
) : PagingSource<Int, StoryResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryResponse> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val stories = api.getStories(token, position, params.loadSize).listStory
            LoadResult.Page(
                data = stories ?: listOf(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (stories.isNullOrEmpty()) null else position + 1
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StoryResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {

        private const val INITIAL_PAGE_INDEX = 1
    }
}