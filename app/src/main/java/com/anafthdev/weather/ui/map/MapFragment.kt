package com.anafthdev.weather.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anafthdev.weather.R
import com.anafthdev.weather.databinding.FragmentMapBinding
import com.anafthdev.weather.foundation.extension.deviceLocale
import com.anafthdev.weather.foundation.extension.toast
import com.anafthdev.weather.model.geocoding.City
import com.anafthdev.weather.ui.map.data.LocationChangeCallback
import com.anafthdev.weather.ui.map.data.LocationChangeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback, LocationChangeListener {
	
	private val DEFAULT_INTERVAL = 1000L
	private val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL * 5
	
	private lateinit var binding: FragmentMapBinding
	private lateinit var navController: NavController
	
	private lateinit var mapBoxMap: MapboxMap
	private lateinit var locationEngine: LocationEngine
	private lateinit var locationManager: LocationManager
	
	private val mapViewModel: MapViewModel by viewModels()
	
	private var state: MapState = MapState()
	
	private val callback: LocationChangeCallback = LocationChangeCallback(this)
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMapBinding.inflate(inflater)
		return binding.root
	}
	
	@SuppressLint("MissingPermission")
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		navController = findNavController()
		
		(requireActivity() as AppCompatActivity).supportActionBar?.let {
			it.setDisplayHomeAsUpEnabled(true)
			it.setDisplayShowHomeEnabled(true)
		}
		
		lifecycleScope.launch {
			mapViewModel.state.collect { newState ->
				state = newState
			}
		}
		
		locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
		locationManager.requestLocationUpdates(
			LocationManager.GPS_PROVIDER,
			TimeUnit.MINUTES.toMillis(2),
			10f
		) { location ->
			mapViewModel.dispatch(
				MapAction.UpdateLocation(
					location = location
				)
			)
		}
		
		binding.mapViewMapFragment.onCreate(savedInstanceState)
		
		
		initUI()
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		
		when (item.itemId) {
			android.R.id.home -> navController.popBackStack()
		}
		
		return true
	}
	
	@SuppressLint("MissingPermission")
	override fun onMapReady(mapboxMap: MapboxMap) {
		Timber.i("MapBox: map ready")
		this.mapBoxMap = mapboxMap
		
		this.mapBoxMap.let { map ->
			map.addOnCameraMoveListener {
				binding.myLocationMapFragment.setImageResource(R.drawable.ic_location_searching)
			}
			
			map.setStyle(Style.TRAFFIC_DAY) { style ->
				Timber.i("MapBox: style loaded -> ${style.uri}")
				val locationComponentActivationOptions =
					LocationComponentActivationOptions.builder(requireContext(), style)
						.useDefaultLocationEngine(false)
						.build()
				
				this.mapBoxMap.locationComponent.let { lc ->
					lc.activateLocationComponent(locationComponentActivationOptions)
					lc.isLocationComponentEnabled = true
					lc.cameraMode = CameraMode.TRACKING
					lc.renderMode = RenderMode.GPS
				}
				
				locationEngine = LocationEngineProvider.getBestLocationEngine(requireContext())
				val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL)
					.setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
					.setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()
				locationEngine.requestLocationUpdates(request, callback, Looper.getMainLooper())
				locationEngine.getLastLocation(callback)
			}
		}
		
	}
	
	override fun onLowMemory() {
		super.onLowMemory()
		binding.mapViewMapFragment.onLowMemory()
	}
	
	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		binding.mapViewMapFragment.onSaveInstanceState(outState)
	}
	
	override fun onPause() {
		super.onPause()
		binding.mapViewMapFragment.onPause()
	}
	
	override fun onResume() {
		super.onResume()
		binding.mapViewMapFragment.onResume()
	}
	
	override fun onStop() {
		super.onStop()
		binding.mapViewMapFragment.onStop()
	}
	
	override fun onStart() {
		super.onStart()
		binding.mapViewMapFragment.onStart()
	}
	
	override fun onDestroy() {
		super.onDestroy()
		binding.mapViewMapFragment.onDestroy()
	}
	
	private fun initUI() {
		binding.mapViewMapFragment.getMapAsync(this)
		
		binding.saveMapFragment.setOnClickListener {
			val isValid = try {
				val latitude = mapBoxMap.cameraPosition.target.latitude
				val longitude = mapBoxMap.cameraPosition.target.longitude
				val address = Geocoder(requireContext(), deviceLocale).getFromLocation(
					latitude,
					longitude,
					1
				) as List<Address>
				
				mapViewModel.dispatch(
					MapAction.InsertCity(
						city = City(
							id = Random.nextInt(),
							name = address[0].locality,
							latitude = latitude,
							longitude = longitude,
							admin1 = address[0].adminArea,
							admin1_id = Random.nextInt(),
							admin2 = address[0].subAdminArea,
							admin2_id = Random.nextInt(),
							admin3 = address[0].subAdminArea,
							admin3_id = Random.nextInt(),
							country = deviceLocale.displayCountry,
							country_code = deviceLocale.country,
							country_id = Random.nextInt(),
							elevation = 0.0,
							feature_code = "",
							population = 0,
							ranking = 0.0,
							timezone = "",
							postcodes = null
						)
					)
				)
				
				true
			} catch (e: Exception) {
				false
			}
			
			if (isValid) navController.popBackStack()
			else getString(R.string.invalid_location).toast(requireContext())
		}
		
		binding.myLocationMapFragment.setOnClickListener { view ->
			mapBoxMap.animateCamera({
				CameraPosition.Builder()
					.target(LatLng(state.location))
					.zoom(16.0)
					.build()
			}, object : MapboxMap.CancelableCallback {
				override fun onCancel() {}
				
				override fun onFinish() {
					(view as FloatingActionButton).setImageResource(R.drawable.ic_my_location)
				}
			})
		}
	}
	
	private fun updateUI() {
	
	}
	
	override fun onLocationChanged(location: Location) {
		lifecycleScope.launch {
			mapViewModel.dispatch(
				MapAction.UpdateLocation(
					location = location
				)
			)
		}
	}
	
}