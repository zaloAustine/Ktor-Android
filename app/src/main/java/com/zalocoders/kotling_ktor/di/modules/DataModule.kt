package com.zalocoders.kotling_ktor.di.modules

import android.net.http.HttpResponseCache.install
import com.zalocoders.kotling_ktor.base.utils.ResponseMapper
import com.zalocoders.kotling_ktor.data.repository.KtorUnsplashRepositoryImpl
import com.zalocoders.kotling_ktor.domain.repository.UnsplashRepository
import dagger.Provides
import java.security.spec.PSSParameterSpec.DEFAULT
import java.util.logging.Logger
import javax.inject.Singleton

object DataModule {
	
	@Singleton
	@Provides
	fun provideKtorHttpClient(): HttpClient {
		return HttpClient(OkHttp) {
			install(JsonFeature) {
				GsonSerializer()
			}
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
		}
	}
	
	@Singleton
	@Provides
	fun provideUnsplashService(
			retrofit: Retrofit,
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