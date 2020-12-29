package com.xdwin.moov.features.homelist.dagger

import com.xdwin.common.viewmodel.dagger.CommonViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [CommonViewModelModule::class])
interface HomeListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeListComponent
    }

    fun inject(homeListFragment: com.xdwin.moov.features.homelist.HomeListFragment)
}