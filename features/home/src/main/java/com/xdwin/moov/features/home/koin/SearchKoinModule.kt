package com.xdwin.moov.features.home.koin

import com.xdwin.moov.features.home.search.SearchRepository
import com.xdwin.moov.features.home.search.SearchRepositoryImpl
import com.xdwin.moov.features.home.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchKoinModule = module {
    single<SearchRepository> { SearchRepositoryImpl() }
    viewModel { SearchViewModel(get()) }
}