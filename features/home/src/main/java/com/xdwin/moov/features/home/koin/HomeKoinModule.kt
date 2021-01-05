package com.xdwin.moov.features.home.koin

import com.xdwin.common.viewmodel.repository.HomeRepository
import com.xdwin.common.viewmodel.repository.HomeRepositoryImpl
import com.xdwin.common.viewmodel.vm.NowPlayingViewModel
import com.xdwin.common.viewmodel.vm.PopularViewModel
import com.xdwin.common.viewmodel.vm.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeKoinModule = module {
    single<HomeRepository> { HomeRepositoryImpl() }
    viewModel { NowPlayingViewModel(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { PopularViewModel(get()) }
}