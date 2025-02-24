package com.example.simplevideoplayerfortestlib.domain.repository

import com.example.simplevideoplayerfortestlib.domain.models.VideoItem

interface UserRepository {

    suspend fun getListOfVideo(): List<VideoItem>

    suspend fun getVideoUseCase(position: Int): VideoItem

}