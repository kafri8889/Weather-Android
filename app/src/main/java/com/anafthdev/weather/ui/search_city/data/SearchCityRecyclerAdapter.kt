package com.anafthdev.weather.ui.search_city.data

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.anafthdev.weather.R
import com.anafthdev.weather.data.diffutil.CityDiffUtil
import com.anafthdev.weather.data.networking.geocoding.GeocodingClient
import com.anafthdev.weather.databinding.CitySearchBinding
import com.anafthdev.weather.model.geocoding.City
import timber.log.Timber

class SearchCityRecyclerAdapter: ListAdapter<City,SearchCityRecyclerAdapter.ViewHolder>(
	CityDiffUtil()
) {
	
	inner class ViewHolder(
		private val context: Context,
		private val binding: CitySearchBinding
	): RecyclerView.ViewHolder(binding.root) {
		
		private val imageLoader = ImageLoader.Builder(context)
			.componentRegistry { add(SvgDecoder(context)) }
			.build()
		
		fun bind(city: City) {
			val request = ImageRequest.Builder(context)
				.placeholder(ColorDrawable(Color.GRAY))
				.error(ColorDrawable(Color.GRAY))
				.data(GeocodingClient.getFlagImage(city.country_code.lowercase()))
				.target(binding.flagImageCitySearch)
				.build()
			
			imageLoader.enqueue(request)
			
			binding.cityCitySearch.text = city.name
			binding.countryCitySearch.text = city.country
			binding.root.setOnClickListener {
			
			}
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			parent.context,
			CitySearchBinding.bind(
				LayoutInflater.from(parent.context).inflate(R.layout.city_search, parent, false)
			)
		)
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
	
}