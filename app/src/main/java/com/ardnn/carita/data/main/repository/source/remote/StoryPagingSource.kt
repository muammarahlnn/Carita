package com.ardnn.carita.data.main.repository.source.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.ardnn.carita.data.main.repository.source.remote.response.StoriesResponse
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoryPagingSource @Inject constructor(
    private val api: StoryDicodingApi,
    private val token: String,
) : RxPagingSource<Int, StoryResponse>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, StoryResponse>> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        return api.getStories(token, position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                toLoadResult(it, position)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, StoryResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun toLoadResult(data: StoriesResponse, position: Int): LoadResult<Int, StoryResponse> {
        return LoadResult.Page(
            data = data.listStory ?: listOf(),
            prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
            nextKey = if (data.listStory?.isEmpty() == true) null else position + 1
        )
    }

    companion object {

        private const val INITIAL_PAGE_INDEX = 1
    }
}