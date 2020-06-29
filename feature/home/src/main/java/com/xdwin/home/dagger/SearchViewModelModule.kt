package com.xdwin.home.dagger

import androidx.lifecycle.ViewModel
import com.xdwin.abstraction.dagger.ViewModelKey
import com.xdwin.home.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel
}