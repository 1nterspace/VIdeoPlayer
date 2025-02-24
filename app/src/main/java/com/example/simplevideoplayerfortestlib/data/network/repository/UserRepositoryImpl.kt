package com.example.simplevideoplayerfortestlib.data.network.repository

import com.example.simplevideoplayerfortestlib.data.network.UserNetwork
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.example.simplevideoplayerfortestlib.domain.repository.UserRepository

class UserRepositoryImpl(private val userNetwork: UserNetwork):UserRepository {

    override suspend fun getListOfVideo(): List<VideoItem> {
        return userNetwork.getListOfVide()
    }

    override suspend fun getVideoUseCase(position: Int): VideoItem {
        return userNetwork.grtVideo(position)
    }
}