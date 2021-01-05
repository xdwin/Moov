package com.xdwin.moov

import android.app.Application
import com.xdwin.moov.features.detail.koin.detailKoinModule
import com.xdwin.moov.features.home.koin.homeKoinModule
import com.xdwin.moov.features.home.koin.searchKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoovApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoovApplication)
            modules(homeKoinModule, searchKoinModule, detailKoinModule)
        }
    }
}