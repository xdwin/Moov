package com.xdwin.homelist.dagger

import com.xdwin.common.viewmodel.dagger.CommonViewModelModule
import com.xdwin.homelist.HomeListFragment
import dagger.Subcomponent

@Subcomponent(modules = [CommonViewModelModule::class])
interface HomeListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeListComponent
    }

    fun inject(homeListFragment: HomeListFragment)
}