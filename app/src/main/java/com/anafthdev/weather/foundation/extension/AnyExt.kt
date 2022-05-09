package com.anafthdev.weather.foundation.extension

import android.content.Context
import android.widget.Toast

fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) =
	Toast.makeText(context, this.toString(), length).show()
