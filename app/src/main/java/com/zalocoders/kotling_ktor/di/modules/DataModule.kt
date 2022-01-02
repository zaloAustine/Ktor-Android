package com.zalocoders.kotling_ktor.di.modules

import com.zalocoders.kotling_ktor.base.utils.ResponseMapper
import com.zalocoders.kotling_ktor.data.repository.KtorUnsplashRepositoryImpl
import com.zalocoders.kotling_ktor.domain.repository.UnsplashRepository
import com.zalocoders.kotling_ktor.remote.KtorUnsplashService
import com.zalocoders.kotling_ktor.remote.UnsplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
	
	@Singleton
	@Provides
	fun provideKtorHttpClient(): HttpClient {
		return HttpClient(OkHttp) {
			defaultRequest {
				headers {
					append("Accept-Version", "v1")
					append(HttpHeaders.Authorization, "Client-ID ti90oMOJyxTN-gKrvE39bi6LM2tbMAdOvey4QMKES0k")
				}
				url {
					protocol = URLProtocol.HTTPS
					host = "api.unsplash.com"
				}
			}
			install(JsonFeature) {
				GsonSerializer()
			}
			install(Logging) {
				logger = io.ktor.client.features.logging.Logger.DEFAULT
				level = LogLevel.ALL
			}
		}
	}
	
	@Singleton
	@Provides
	fun provideUnsplashService(
			okHttpClient: HttpClient
	): UnsplashService {
		return KtorUnsplashService(okHttpClient)
	}
	
	@Singleton
	@Provides
	fun provideUnsplashRepository(
			unsplashService: UnsplashService
	): UnsplashRepository {
		return KtorUnsplashRepositoryImpl(unsplashService, ResponseMapper::responseToPhotoList)
	}
}