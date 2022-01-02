package com.zalocoders.kotling_ktor.domain.usecases

import com.zalocoders.kotling_ktor.domain.repository.UnsplashRepository
import javax.inject.Inject


class GetSearchResultUseCase @Inject constructor(
		private val unsplashRepository: UnsplashRepository
) {
	
	fun <T> execute(query: String) = unsplashRepository.getSearchResult<T>(query)
}
