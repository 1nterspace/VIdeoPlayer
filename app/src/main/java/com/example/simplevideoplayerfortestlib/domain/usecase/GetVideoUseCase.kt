package com.example.simplevideoplayerfortestlib.domain.usecase

import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.example.simplevideoplayerfortestlib.domain.repository.UserRepository


class GetVideoUseCase(private val userRepository: UserRepository) {

    suspend fun execute(position: Int): VideoItem {
        return userRepository.getVideoUseCase(position)
    }

}