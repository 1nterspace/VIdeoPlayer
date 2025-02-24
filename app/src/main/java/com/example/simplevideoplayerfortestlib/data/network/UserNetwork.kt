package com.example.simplevideoplayerfortestlib.data.network

import com.example.simplevideoplayerfortestlib.domain.models.VideoItem

interface UserNetwork {

    suspend fun getListOfVide(): List<VideoItem>

    suspend fun grtVideo(position: Int): VideoItem

}