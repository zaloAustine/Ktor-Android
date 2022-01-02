package com.zalocoders.kotling_ktor.domain.repository

import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
	fun <T> getSearchResult(query: String): Flow<T>
}