package com.xdwin.moov.features.home.dagger

import androidx.lifecycle.ViewModel
import com.xdwin.abstraction.dagger.ViewModelKey
import com.xdwin.moov.features.home.search.SearchRepository
import com.xdwin.moov.features.home.search.SearchRepositoryImpl
import com.xdwin.moov.features.home.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchViewModelModule {

    companion object {
        @Provides
        fun searchRepository(): SearchRepository {
            return SearchRepositoryImpl()
        }
    }
}