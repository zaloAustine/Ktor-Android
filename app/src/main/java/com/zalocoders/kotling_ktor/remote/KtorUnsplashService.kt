package com.zalocoders.kotling_ktor.remote

import com.zalocoders.kotling_ktor.data.model.UnsplashResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorUnsplashService @Inject constructor(
		private val httpClient: HttpClient
) : UnsplashService {
	
	override suspend fun searchPhotos(
			query: String,
			page: Int,
			perPage: Int
	): com.zalocoders.kotling_ktor.base.utils.Result<UnsplashResponse> {
		return try {
			val response = httpClient.get<HttpResponse>(path = "search/photos") {
				parameter("query", query)
				parameter("page", page)
				parameter("per_page", perPage)
			}
			
			if (response.status.isSuccess()) {
				com.zalocoders.kotling_ktor.base.utils.Result.Success(response.receive(), response.status.value)
			} else {
				com.zalocoders.kotling_ktor.base.utils.Result.ApiError(response.status.description, response.status.value)
			}
		} catch (e: Exception) {
			com.zalocoders.kotling_ktor.base.utils.Result.NetworkError(e)
		}
	}}