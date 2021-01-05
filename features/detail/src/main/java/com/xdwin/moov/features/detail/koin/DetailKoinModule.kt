package com.xdwin.moov.features.detail.koin

import com.xdwin.moov.features.detail.detail.DetailRepository
import com.xdwin.moov.features.detail.detail.DetailRepositoryImpl
import com.xdwin.moov.features.detail.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailKoinModule = module {
    single<DetailRepository> { DetailRepositoryImpl() }
    viewModel { DetailViewModel(get()) }
}