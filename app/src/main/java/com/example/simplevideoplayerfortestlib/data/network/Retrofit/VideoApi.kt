package com.example.simplevideoplayerfortestlib.data.network.Retrofit

import com.example.simplevideoplayerfortestlib.domain.models.VideoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface VideoApi {

    @GET("videos")
    suspend fun getVideos(
        @Header("Authorization") token: String = "Bearer NcdEJyXQvAvF5QUSVi1oVEI2AuOXa5TY6VDAl8CjrJz"
    )
            : Response<VideoListResponse>
}