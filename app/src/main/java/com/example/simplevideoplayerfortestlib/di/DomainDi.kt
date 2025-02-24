package com.example.simplevideoplayerfortestlib.di

import com.example.simplevideoplayerfortestlib.domain.usecase.GetListOfVideoUseCase
import com.example.simplevideoplayerfortestlib.domain.usecase.GetVideoUseCase
import org.koin.dsl.module

val domainDi = module {

    factory <GetListOfVideoUseCase> {
        GetListOfVideoUseCase(userRepository = get())
    }

    factory <GetVideoUseCase> {
        GetVideoUseCase(userRepository = get())
    }

}