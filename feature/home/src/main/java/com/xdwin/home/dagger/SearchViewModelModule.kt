package com.xdwin.home.dagger

import androidx.lifecycle.ViewModel
import com.xdwin.abstraction.dagger.ViewModelKey
import com.xdwin.home.search.SearchRepository
import com.xdwin.home.search.SearchRepositoryImpl
import com.xdwin.home.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    companion object {
        @Provides
        fun searchRepository(): SearchRepository {
            return SearchRepositoryImpl()
        }
    }
}