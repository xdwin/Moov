package com.xdwin.moov

import android.app.Application
import com.xdwin.moov.features.detail.dagger.DetailComponent
import com.xdwin.moov.features.detail.dagger.DetailComponentCreator
import com.xdwin.moov.features.home.dagger.HomeComponent
import com.xdwin.moov.features.home.dagger.HomeComponentCreator
import com.xdwin.moov.features.homelist.dagger.HomeListComponent
import com.xdwin.moov.features.homelist.dagger.HomeListComponentCreator

class MoovApplication : Application(),
    HomeComponentCreator,
    HomeListComponentCreator,
    DetailComponentCreator{

    val appComponent = DaggerAppComponent.create()

    override fun createHomeComponent(): HomeComponent = appComponent.homeComponent().create()

    override fun createDetailComponent(): DetailComponent = appComponent.detailComponent().create()

    override fun createHomeListComponent(): HomeListComponent = appComponent.homeListComponent().create()
}