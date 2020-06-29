package com.xdwin.detail.dagger

import com.xdwin.detail.detail.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailViewModelModule::class])
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(detailFragment: DetailFragment)
}