package com.anafthdev.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anafthdev.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	
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
		
		setSupportActionBar(binding.toolbarMainActivity.toolbar)
	}
}