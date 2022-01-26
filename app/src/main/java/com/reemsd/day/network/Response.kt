package com.reemsd.day.network

import com.squareup.moshi.Json



data class TopPlaces(

	@Json(name="image")
	val image: String? = null,

	@Json(name="reviews")
	val reviews: String? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="location")
	val location: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="type")
	val type: String? = null
)


