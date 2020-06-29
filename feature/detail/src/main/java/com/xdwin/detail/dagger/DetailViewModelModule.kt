package com.xdwin.detail.dagger

import androidx.lifecycle.ViewModel
import com.xdwin.abstraction.dagger.ViewModelKey
import com.xdwin.detail.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel
}
