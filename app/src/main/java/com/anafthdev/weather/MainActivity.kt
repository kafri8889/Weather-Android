package com.anafthdev.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		if (BuildConfig.DEBUG) {
			Timber.plant(object : Timber.DebugTree() {
				override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
					super.log(priority, "DEBUG_$tag", message, t)
				}
			})
		}
	}
}