package com.example.simplevideoplayerfortestlib.di

import com.example.simplevideoplayerfortestlib.presentation.DataModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationDi = module {

    viewModel<DataModel>{
        DataModel(
            getListOfVideoUseCase = get(),
            getVideoUseCase = get()
        )
    }

}