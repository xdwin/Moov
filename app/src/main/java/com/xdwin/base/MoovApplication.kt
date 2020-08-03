package com.xdwin.base

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
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

    override fun onCreate() {
        super.onCreate()
        // Facebook SoLoader
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).also {
                it.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
                it.addPlugin(NetworkFlipperPlugin())
                it.start()
            }
        }
    }
}