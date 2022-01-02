package com.zalocoders.kotling_ktor.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zalocoders.kotling_ktor.data.model.UnsplashPhoto
import com.zalocoders.kotling_ktor.domain.usecases.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
		getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {
	
	val searchResult = getSearchResultUseCase
			.execute<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
			.cachedIn(viewModelScope)
			.asLiveData()
	
	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}