package com.example.simplevideoplayerfortestlib.data.network

import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.example.simplevideoplayerfortestlib.domain.models.VideoListResponse

interface UserNetwork {

    suspend fun getListOfVide(): List<VideoItem>

    suspend fun grtVideo(position:Int): VideoItem

}