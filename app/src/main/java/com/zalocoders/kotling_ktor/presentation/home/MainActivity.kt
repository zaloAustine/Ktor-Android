package com.zalocoders.kotling_ktor.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.zalocoders.kotling_ktor.R
import com.zalocoders.kotling_ktor.presentation.viewModel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	private val galleryViewModel:GalleryViewModel by viewModels()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		galleryViewModel.searchResult.observe(this){
			//paging data
		}
	}
}