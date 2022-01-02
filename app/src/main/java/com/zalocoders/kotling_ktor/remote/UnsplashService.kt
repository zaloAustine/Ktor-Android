package com.zalocoders.kotling_ktor.remote

import com.zalocoders.kotling_ktor.base.utils.Result
import com.zalocoders.kotling_ktor.data.model.UnsplashResponse

interface UnsplashService {
	suspend fun searchPhotos(query: String, page: Int = 1, perPage: Int = 20): Result<UnsplashResponse>
	
}