package com.example.simplevideoplayerfortestlib.domain

import com.example.simplevideoplayerfortestlib.domain.models.Assets
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.example.simplevideoplayerfortestlib.domain.repository.UserRepository
import com.example.simplevideoplayerfortestlib.domain.usecase.GetListOfVideoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test

class TestRepository:UserRepository{
    override suspend fun getListOfVideo(): List<VideoItem> {
        val list = mutableListOf<VideoItem>(
            VideoItem("1","title1", Assets("thumbnail1","mp4")),
            VideoItem("2","title2", Assets("thumbnail2","mp4")),
            VideoItem("3","title3", Assets("thumbnail3","mp4")),
            VideoItem("4","title4", Assets("thumbnail4","mp4")),
        )
        return list.shuffled()
    }

    override suspend fun getVideoUseCase(position: Int): VideoItem {
        TODO("Not yet implemented")
    }

}


class GetListOfVideoUseCaseTest {


    @Test
    fun shouldReturnCorrectData(){
        val testRepository = TestRepository()
        val getListOfVideoUseCase = GetListOfVideoUseCase(testRepository)
        CoroutineScope(Dispatchers.Main).launch{
            val actual = getListOfVideoUseCase.execute()
            val expected = mutableListOf<VideoItem>(
            VideoItem("1","title1", Assets("thumbnail1","mp4")),
            VideoItem("2","title2", Assets("thumbnail2","mp4")),
            VideoItem("3","title3", Assets("thumbnail3","mp4")),
            VideoItem("4","title4", Assets("thumbnail4","mp4")),
            )

            Assert.assertSame(expected,actual)
        }

    }

}