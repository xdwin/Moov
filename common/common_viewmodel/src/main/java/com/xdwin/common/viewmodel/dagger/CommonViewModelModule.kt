package com.xdwin.common.viewmodel.dagger

import androidx.lifecycle.ViewModel
import com.xdwin.abstraction.dagger.ViewModelKey
import com.xdwin.common.viewmodel.vm.NowPlayingViewModel
import com.xdwin.common.viewmodel.vm.PopularViewModel
import com.xdwin.common.viewmodel.vm.TopRatedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CommonViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    internal abstract fun nowPlayingViewModel(viewModel: NowPlayingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    internal abstract fun popularViewModel(viewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    internal abstract fun topRatedViewModel(viewModel: TopRatedViewModel): ViewModel
}