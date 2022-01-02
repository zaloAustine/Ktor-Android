package com.zalocoders.kotling_ktor.base.utils

import com.zalocoders.kotling_ktor.data.model.UnsplashPhoto
import com.zalocoders.kotling_ktor.data.model.UnsplashResponse

object ResponseMapper {
	
	fun responseToPhotoList(response: Result<UnsplashResponse>): Result<List<UnsplashPhoto>> {
		return when(response) {
			is Result.Success -> Result.Success(response.data.results, response.code)
			is Result.ApiError -> Result.ApiError(response.message, response.code)
			is Result.NetworkError -> Result.NetworkError(response.throwable)
			is Result.NullResult -> Result.NullResult()
			is Result.Loading -> Result.Loading()
		}
	}
}