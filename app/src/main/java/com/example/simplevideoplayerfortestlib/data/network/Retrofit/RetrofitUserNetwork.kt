package com.example.simplevideoplayerfortestlib.data.network.Retrofit

import android.util.Log
import com.example.simplevideoplayerfortestlib.data.network.UserNetwork
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://sandbox.api.video"

class RetrofitUserNetwork : UserNetwork {


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiVideoService: VideoApi = retrofit.create(VideoApi::class.java)

    override suspend fun getListOfVide(): List<VideoItem> {

        return try {
            val response = apiVideoService.getVideos()
            if (!response.isSuccessful) {
                Log.e("API_ERROR", "Unsuccessful response: ${response.code()}")
                return emptyList()
            }

            val videoListResponse = response.body()
            if (videoListResponse == null) {
                Log.e("API_ERROR", "Response body is null")
                return emptyList()
            }

            val videos = videoListResponse.data
            Log.d("API_RESPONSE", "Videos: $videos")

            videos

        } catch (e: Exception) {
            Log.e("API_ERROR", "Error fetching videos", e)
            emptyList()
        }
    }

    override suspend fun grtVideo(position: Int): VideoItem {

        val list = getListOfVide()

        if (position < 0 || position >= list.size) {
            Log.e("API_ERROR", "Invalid position: $position")
            throw IndexOutOfBoundsException("Invalid position: $position")
        }

        return list[position]
    }

}