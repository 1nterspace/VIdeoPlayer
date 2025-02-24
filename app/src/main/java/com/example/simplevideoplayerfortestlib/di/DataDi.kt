package com.example.simplevideoplayerfortestlib.di

import com.example.simplevideoplayerfortestlib.data.network.Retrofit.RetrofitUserNetwork
import com.example.simplevideoplayerfortestlib.data.network.UserNetwork
import com.example.simplevideoplayerfortestlib.data.network.repository.UserRepositoryImpl
import com.example.simplevideoplayerfortestlib.domain.repository.UserRepository
import org.koin.dsl.module

val dataDi = module {

    single <UserNetwork> {
        RetrofitUserNetwork()
    }

    single <UserRepository> {
        UserRepositoryImpl(userNetwork = get())
    }

}