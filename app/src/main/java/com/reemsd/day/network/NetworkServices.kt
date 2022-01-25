package com.reemsd.day.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// this is url to bring data from web.
const val Base_url = "https://funday-eb035-default-rtdb.firebaseio.com/"

//
fun getInterceptor(): Interceptor {
    var interceptor = HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor
}

val client = OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
//this is moshi convert json into kotlin object .
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//we need retrofit to talk to web server and receive data in json format
// and addConverterFactory will converted data to kotlin object useing moshi
//and baseUrl(BASE_URL) the link of data.
private val retrofit = Retrofit.Builder().client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Base_url)
    .build()

interface TopServices {
    @GET("topten.json")
    suspend fun getTopten():Map<String,TopPlaces>
    @GET("userPlan.json")

    suspend fun userPlan():Map<String,PlanData>

}

object RealTimeData {
    val retrofitServices :TopServices by lazy {
        retrofit.create(TopServices::class.java)
    }

}
