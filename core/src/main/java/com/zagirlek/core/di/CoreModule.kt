package com.zagirlek.core.di

import androidx.room.Room
import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.local.dao.CharacterDao
import com.zagirlek.core.local.dao.RemoteKeyDao
import com.zagirlek.core.network.service.CharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }


    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CharacterService> {
        get<Retrofit>().create(CharacterService::class.java)
    }

    single<CharacterDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = CharacterDatabase::class.java,
            name = "characterDatabase"
        ).build()
    }

    single<CharacterDao> { get<CharacterDatabase>().characterDao() }

    single<RemoteKeyDao> { get<CharacterDatabase>().remoteKeyDao() }
}