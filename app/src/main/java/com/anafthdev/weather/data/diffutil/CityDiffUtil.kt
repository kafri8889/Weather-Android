package com.anafthdev.weather.data.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.anafthdev.weather.model.geocoding.City

class CityDiffUtil: DiffUtil.ItemCallback<City>() {
	
	override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
		return oldItem.id == newItem.id
	}
	
	override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
		return false
	}
	
}