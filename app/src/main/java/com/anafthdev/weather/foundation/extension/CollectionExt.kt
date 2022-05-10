package com.anafthdev.weather.foundation.extension

/**
 * Return a item containing only elements matching the given [predicate].
 * @author kafri8889
 */
fun <T> Collection<T>.get(predicate: (T) -> Boolean): T? {
	this.forEach {
		if (predicate(it)) return it
	}
	
	return null
}

/**
 * Get index element of given predicate.
 * if element is not in list, return -1
 * @author kafri8889
 */
fun <T> Collection<T>.indexOf(predicate: (T) -> Boolean): Int {
	this.forEachIndexed { i, t ->
		if (predicate(t)) return i
	}
	
	return -1
}
