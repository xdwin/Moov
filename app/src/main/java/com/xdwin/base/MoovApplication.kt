package com.xdwin.base

import android.app.Application
import com.xdwin.detail.dagger.DetailComponent
import com.xdwin.detail.dagger.DetailComponentCreator
import com.xdwin.home.dagger.HomeComponent
import com.xdwin.home.dagger.HomeComponentCreator
import com.xdwin.homelist.dagger.HomeListComponent
import com.xdwin.homelist.dagger.HomeListComponentCreator

class MoovApplication : Application(),
    HomeComponentCreator,
    HomeListComponentCreator,
    DetailComponentCreator{

    val appComponent = DaggerAppComponent.create()

    override fun createHomeComponent(): HomeComponent = appComponent.homeComponent().create()

    override fun createDetailComponent(): DetailComponent = appComponent.detailComponent().create()

    override fun createHomeListComponent(): HomeListComponent = appComponent.homeListComponent().create()
}