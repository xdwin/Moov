package com.xdwin.home.dagger

import com.xdwin.common.viewmodel.dagger.CommonViewModelModule
import com.xdwin.home.home.HomeFragment
import com.xdwin.home.search.SearchFragment
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