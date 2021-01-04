package com.xdwin.moov.features.detail.dagger

import com.xdwin.moov.features.detail.detail.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailViewModelModule::class])
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(detailFragment: DetailFragment)
}