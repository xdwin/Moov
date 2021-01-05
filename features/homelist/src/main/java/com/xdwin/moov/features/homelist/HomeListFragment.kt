package com.xdwin.moov.features.homelist

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.ext.setupGridAdapter
import com.xdwin.common.viewmodel.HomeViewModelType
import com.xdwin.common.viewmodel.vm.BaseMovieViewModel
import com.xdwin.common.viewmodel.vm.NowPlayingViewModel
import com.xdwin.common.viewmodel.vm.PopularViewModel
import com.xdwin.common.viewmodel.vm.TopRatedViewModel
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.Movies
import kotlinx.android.synthetic.main.fragment_homelist.*
import org.koin.android.ext.android.get
import org.koin.core.qualifier.qualifier

class HomeListFragment : BaseFragment(R.layout.fragment_homelist) {

    lateinit var homeViewModelType: HomeViewModelType
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
            homeViewModelType = getSerializableExtra(Constants.INTENT_HOME_LIST_MODE_KEY) as HomeViewModelType
        }

        viewModel = when(homeViewModelType) {
            HomeViewModelType.NOWPLAYING -> get() as NowPlayingViewModel
            HomeViewModelType.TOPRATED -> get() as TopRatedViewModel
            HomeViewModelType.POPULAR -> get() as PopularViewModel
        }
    }

    override fun initView() {
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView.setupGridAdapter(adapter, 3)
    }

    private fun observeViewModel() {
        viewModel.fetchData()
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