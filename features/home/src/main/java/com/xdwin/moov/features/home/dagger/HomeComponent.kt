package com.xdwin.moov.features.home.dagger

import com.xdwin.common.viewmodel.dagger.CommonViewModelModule
import com.xdwin.moov.features.home.home.HomeFragment
import com.xdwin.moov.features.home.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [CommonViewModelModule::class, SearchViewModelModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(searchFragment: SearchFragment)
}