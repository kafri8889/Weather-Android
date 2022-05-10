package com.anafthdev.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.anafthdev.weather.databinding.ActivityMainBinding
import com.anafthdev.weather.foundation.extension.toast
import com.mapbox.mapboxsdk.Mapbox
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	
	private val resultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
		if (!permissions.all { it.value }) {
			getString(R.string.you_must_allow_location_access).toast(this)
			finishAffinity()
		}
	}
	
	private val permissions = arrayOf(
		Manifest.permission.ACCESS_FINE_LOCATION,
		Manifest.permission.ACCESS_COARSE_LOCATION,
	)
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		if (BuildConfig.DEBUG) {
			Timber.plant(object : Timber.DebugTree() {
				override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
					super.log(priority, "DEBUG_$tag", message, t)
				}
			})
		}
		
		setSupportActionBar(binding.toolbar)
		
		if (!permissions.all { ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
			resultLauncher.launch(permissions)
		}
		
		Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
	}
}