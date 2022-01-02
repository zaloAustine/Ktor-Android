package com.zalocoders.kotling_ktor.data.model

import com.google.gson.annotations.SerializedName


data class UnsplashPhoto(
			val id: String,
			val description: String?,
			val urls: UnsplashPhotoUrls,
			val user: UnsplashUser
	) {
		
		data class UnsplashPhotoUrls(
				val raw: String,
				val full: String,
				val regular: String,
				val small: String,
				val thumb: String,
		)
		
		data class UnsplashUser(
				val name: String,
				val username: String
		)
	}

data class UnsplashResponse(
		val results: List<UnsplashPhoto>,
		@SerializedName("total_pages")
		val totalPages: Int
)