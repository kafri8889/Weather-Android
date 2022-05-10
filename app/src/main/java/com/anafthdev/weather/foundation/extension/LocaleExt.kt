package com.anafthdev.weather.foundation.extension

import android.content.res.Resources
import java.util.*

val deviceLocale: Locale
	get() = Resources.getSystem().configuration.locales[0]
