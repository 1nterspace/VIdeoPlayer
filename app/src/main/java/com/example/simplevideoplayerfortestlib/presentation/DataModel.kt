package com.example.simplevideoplayerfortestlib.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.example.simplevideoplayerfortestlib.domain.usecase.GetListOfVideoUseCase
import com.example.simplevideoplayerfortestlib.domain.usecase.GetVideoUseCase

class DataModel(
    private val getListOfVideoUseCase: GetListOfVideoUseCase,
    private val getVideoUseCase: GetVideoUseCase
) : ViewModel() {

    init {
        Log.d("AAA", "VM created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("AAA", "VM cleared")
    }


    val videoPlayerisActive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val messageToFragmentMP4: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val messageToFragmentTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val messageToActivity: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val resultList = MutableLiveData<List<VideoItem>>()

    //shuffled перемешивает список в случайном порядке
    suspend fun getList() {
        val list = getListOfVideoUseCase.execute()
        resultList.value = list.shuffled()
    }

    suspend fun getVideoItem(position: Int): VideoItem {
        return getVideoUseCase.execute(position)
    }

}


