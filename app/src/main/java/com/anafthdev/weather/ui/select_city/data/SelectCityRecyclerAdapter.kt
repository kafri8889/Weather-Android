package com.anafthdev.weather.ui.select_city.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anafthdev.weather.R
import com.anafthdev.weather.data.diffutil.CityDiffUtil
import com.anafthdev.weather.databinding.CitySelectBinding
import com.anafthdev.weather.foundation.interfaces.OnItemClickListener
import com.anafthdev.weather.model.geocoding.City
import timber.log.Timber

class SelectCityRecyclerAdapter(
	private val listener: OnItemClickListener<City>,
): ListAdapter<City, SelectCityRecyclerAdapter.ViewHolder>(
	CityDiffUtil()
) {
	
	private var selectedCity: City = City.default
	
	inner class ViewHolder(
		private val binding: CitySelectBinding
	): RecyclerView.ViewHolder(binding.root) {
		
		fun bind(city: City) {
			Timber.i("isSelected: ${selectedCity.id} == ${city.id}")
			
			binding.cityCitySelect.text = city.name
			binding.radioButtonCitySelect.isChecked = city.id == selectedCity.id
			binding.radioButtonCitySelect.setOnClickListener {
				listener.onItemClick(city)
			}
			binding.root.setOnClickListener {
				listener.onItemClick(city)
			}
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			CitySelectBinding.bind(
				LayoutInflater.from(parent.context).inflate(R.layout.city_select, parent, false)
			)
		)
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
	
	@SuppressLint("NotifyDataSetChanged")
	fun setSelectedCity(city: City) {
		selectedCity = city
		notifyDataSetChanged()
	}
	
}