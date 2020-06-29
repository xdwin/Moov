package com.xdwin.home.search

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.dagger.ViewModelFactory
import com.xdwin.abstraction.ext.setupVerticalAdapter
import com.xdwin.data.data.Movie
import com.xdwin.home.R
import com.xdwin.home.dagger.HomeComponent
import com.xdwin.home.dagger.HomeComponentCreator
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    lateinit var searchComponent: HomeComponent
    
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

    private var movies = mutableListOf<Movie>()
    private val onMovieClickListener: ((Movie) -> Unit) = {
        val uri = "detail://detail".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.putExtra(Constants.INTENT_MOVIE_ID_KEY, it.id)
        startActivity(intent)
    }

    private val adapter: SearchAdapter by lazy {
        SearchAdapter(
            movies,
            onMovieClickListener
        )
    }

    override fun onAttach(context: Context) {
        searchComponent = (context.applicationContext as HomeComponentCreator).createHomeComponent()
        searchComponent.inject(this)
        super.onAttach(context)
    }

    override fun initDependency() {
        searchViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun initView() {
        setupRecyclerView()
        observeSearchResult()
    }

    private fun setupRecyclerView() {
        recyclerView.setupVerticalAdapter(adapter)
    }

    private fun observeSearchResult() {
        searchViewModel.searchResult.observe(this, Observer {
            if (it.isSuccessful) {
                movies.addAll(it.body()?.results ?: emptyList())
                adapter.notifyDataSetChanged()
            } else {
                // todo show empty results
            }
        })
    }

}