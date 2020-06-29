package com.xdwin.base

import com.xdwin.abstraction.dagger.ViewModelModule
import com.xdwin.detail.dagger.DetailComponent
import com.xdwin.home.dagger.HomeComponent
import com.xdwin.homelist.dagger.HomeListComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, SubcomponentsModule::class])
interface AppComponent {
    fun homeComponent(): HomeComponent.Factory
    fun detailComponent(): DetailComponent.Factory
    fun homeListComponent(): HomeListComponent.Factory
}