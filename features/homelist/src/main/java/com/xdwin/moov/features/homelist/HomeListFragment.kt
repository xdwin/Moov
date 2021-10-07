package com.xdwin.moov.features.homelist

import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.ext.setupGridAdapter
import com.xdwin.common.viewmodel.HomeListMode
import com.xdwin.common.viewmodel.vm.BaseMovieViewModel
import com.xdwin.common.viewmodel.vm.NowPlayingViewModel
import com.xdwin.common.viewmodel.vm.PopularViewModel
import com.xdwin.common.viewmodel.vm.TopRatedViewModel
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.Movies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_homelist.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeListFragment : BaseFragment(R.layout.fragment_homelist) {

    lateinit var homeListMode: HomeListMode
    lateinit var viewModel: BaseMovieViewModel<Movies>

    private var movies = mutableListOf<Movie>()
    private val onMovieClickListener: ((Movie) -> Unit) = {
        val uri = Constants.NAV_URI_DETAIL.toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.putExtra(Constants.INTENT_MOVIE_ID_KEY, it.id)
        startActivity(intent)
    }

    private val adapter by lazy {
        HomeListAdapter(
            movies,
            onMovieClickListener
        )
    }

    override fun initDependency() {
        val intent = activity?.intent
        intent?.run {
            homeListMode = getSerializableExtra(Constants.INTENT_HOME_LIST_MODE_KEY) as HomeListMode
        }
        val vmClass = when(homeListMode) {
            HomeListMode.NOWPLAYING -> NowPlayingViewModel::class.java
            HomeListMode.TOPRATED -> TopRatedViewModel::class.java
            HomeListMode.POPULAR -> PopularViewModel::class.java
        } as Class<BaseMovieViewModel<Movies>>
        viewModel = ViewModelProvider(this).get(vmClass)
    }

    override suspend fun initView() {
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView.setupGridAdapter(adapter, 3)
    }

    private fun observeViewModel() {
        launch(Dispatchers.IO) { viewModel.fetchData() }
        viewModel.observeData().observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {

                }
                is BaseResult.Success -> {
                    movies.addAll(it.data?.results ?: emptyList())
                    adapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {

                }
            }
        })
    }
}