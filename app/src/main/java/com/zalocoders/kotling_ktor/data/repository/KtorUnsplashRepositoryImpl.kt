package com.zalocoders.kotling_ktor.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zalocoders.kotling_ktor.base.utils.Result
import com.zalocoders.kotling_ktor.data.model.UnsplashPhoto
import com.zalocoders.kotling_ktor.data.model.UnsplashResponse
import com.zalocoders.kotling_ktor.domain.repository.UnsplashRepository
import com.zalocoders.kotling_ktor.remote.UnsplashService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class KtorUnsplashRepositoryImpl @Inject constructor(
		private val unsplashService: UnsplashService,
		private val responseToPhotoList: (com.zalocoders.kotling_ktor.base.utils.Result<UnsplashResponse>) -> Result<List<UnsplashPhoto>>
) : UnsplashRepository {
	
	
	override fun <T> getSearchResult(query: String): Flow<T> =
			Pager(
					config = PagingConfig(
							pageSize = 20,
							maxSize = 100,
							enablePlaceholders = false
					),
					pagingSourceFactory = { KtorUnsplashPagingSource(unsplashService, query) }
			).flow as Flow<T>
}



class KtorUnsplashPagingSource constructor(
		private val unsplashService: UnsplashService,
		private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
	override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
		return state.anchorPosition
	}
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
		val position = params.key?: 1
		val response = unsplashService.searchPhotos(query, position, params.loadSize)
		
		return try {
			response as com.zalocoders.kotling_ktor.base.utils.Result.Success
			LoadResult.Page(
					data = response.data.results,
					prevKey = if (position == 1) null else position - 1,
					nextKey = if (position == response.data.totalPages) null else position + 1
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}