package com.example.highcircletmdb.app

import android.app.Application
import com.example.highcircletmdb.data.di.dataModule
import com.example.highcircletmdb.domain.di.domainModule
import com.example.highcircletmdb.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class HighCircleTmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HighCircleTmdbApplication)
            modules(dataModule, domainModule, uiModule)
        }
    }
}