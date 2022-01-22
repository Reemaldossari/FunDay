package com.reemsd.day.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// this is url to bring data from web.
const val Base_url = "https://funday-eb035-default-rtdb.firebaseio.com/"

//this is moshi convert json into kotlin object .
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//we need retrofit to talk to web server and receive data in json format
// and addConverterFactory will converted data to kotlin object useing moshi
//and baseUrl(BASE_URL) the link of data.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Base_url)
    .build()

interface TopServices {
    @GET("topten.json")
    suspend fun getTopten():Map<String,TopPlaces>

}

object RealTimeData {
    val retrofitServices :TopServices by lazy {
        retrofit.create(TopServices::class.java)
    }

}
