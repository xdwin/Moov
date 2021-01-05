package com.xdwin.moov

import android.app.Application
import com.xdwin.moov.features.detail.koin.detailKoinModule
import com.xdwin.moov.features.home.dagger.HomeComponent
import com.xdwin.moov.features.home.dagger.HomeComponentCreator
import com.xdwin.moov.features.homelist.dagger.HomeListComponent
import com.xdwin.moov.features.homelist.dagger.HomeListComponentCreator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoovApplication : Application(),
    HomeComponentCreator,
    HomeListComponentCreator {

    val appComponent = DaggerAppComponent.create()

    override fun createHomeComponent(): HomeComponent = appComponent.homeComponent().create()

    override fun createHomeListComponent(): HomeListComponent = appComponent.homeListComponent().create()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoovApplication)
            modules(detailKoinModule)
        }
    }
}