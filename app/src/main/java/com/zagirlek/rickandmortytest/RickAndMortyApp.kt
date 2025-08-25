package com.zagirlek.rickandmortytest

import android.app.Application
import com.zagirlek.core.di.coreModule
import com.zagirlek.data.di.dataModule
import com.zagirlek.domain.di.domainModule
import com.zagirlek.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                coreModule, dataModule, domainModule, presentationModule
            )
        }

    }
}