package com.xdwin.home.home

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.MergeAdapter
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.listener.BackPressedListener
import com.xdwin.abstraction.abstraction.BaseActivity
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.adapter.StaticAdapter
import com.xdwin.abstraction.ext.setupMergeAdapter
import com.xdwin.common.viewmodel.HomeListMode
import com.xdwin.common.viewmodel.vm.NowPlayingViewModel
import com.xdwin.common.viewmodel.vm.PopularViewModel
import com.xdwin.common.viewmodel.vm.TopRatedViewModel
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.home.R
import com.xdwin.home.dagger.HomeComponent
import com.xdwin.home.dagger.HomeComponentCreator
import com.xdwin.home.home.adapter.HomeCardLoadingAdapter
import com.xdwin.home.home.adapter.HomeCardSectionAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.fragment_home),
    BackPressedListener {

    lateinit var activitySwitchListener: HomeSwitchFragmentListener
    lateinit var homeComponent: HomeComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var topRatedViewModel: TopRatedViewModel

    private var nowPlayingMovies = mutableListOf<Movie>()
    private var topRatedMovies = mutableListOf<Movie>()
    private var popularMovies = mutableListOf<Movie>()

    private val onMovieClickListener: ((Movie) -> Unit) = {
        val uri = Constants.NAV_URI_DETAIL.toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.putExtra(Constants.INTENT_MOVIE_ID_KEY, it.id)
        startActivity(intent)
    }

    // todo @xdwin fix this (above and below) inconsistensies
    fun onSeeAllClickListener(mode: HomeListMode): () -> Unit {
        return {
            val uri = Constants.NAV_URI_HOMELIST.toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.putExtra(Constants.INTENT_HOME_LIST_MODE_KEY, mode)
            startActivity(intent)
        }
    }

    private val onSearchBarClickListener: () -> Unit = {
        activitySwitchListener.switchToSearchFragment()
    }

    private val searchBarAdapter: StaticAdapter by lazy {
        StaticAdapter(
            R.layout.item_search_section,
            onSearchBarClickListener
        )
    }

    private val nowPlayingAdapter: HomeCardSectionAdapter by lazy {
        HomeCardSectionAdapter(
            "Featured",
            nowPlayingMovies,
            onMovieClickListener,
            onSeeAllClickListener(HomeListMode.NOWPLAYING)
        )
    }

    private val topRatedAdapter: HomeCardSectionAdapter by lazy {
        HomeCardSectionAdapter(
            "Top Rated",
            topRatedMovies,
            onMovieClickListener,
            onSeeAllClickListener(HomeListMode.TOPRATED)
        )
    }

    private val popularAdapter: HomeCardSectionAdapter by lazy {
        HomeCardSectionAdapter(
            "Most Popular",
            popularMovies,
            onMovieClickListener,
            onSeeAllClickListener(HomeListMode.POPULAR)
        )
    }

    private val nowPlayingLoadingAdapter: HomeCardLoadingAdapter by lazy {
        HomeCardLoadingAdapter()
    }

    private val topRatedLoadingAdapter: HomeCardLoadingAdapter by lazy {
        HomeCardLoadingAdapter()
    }

    private val popularLoadingAdapter: HomeCardLoadingAdapter by lazy {
        HomeCardLoadingAdapter()
    }

    override fun onAttach(context: Context) {
        homeComponent = (context.applicationContext as HomeComponentCreator).createHomeComponent()
        homeComponent.inject(this)
        if (context is BaseActivity) {
            context.onBackPressedListener = this
        }
        if (context is HomeSwitchFragmentListener) {
            activitySwitchListener = context
        }
        super.onAttach(context)
    }

    override fun initDependency() {
        popularViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PopularViewModel::class.java)
        nowPlayingViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NowPlayingViewModel::class.java)
        topRatedViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TopRatedViewModel::class.java)
    }

    override fun initView() {
        setupRecyclerView()
        observeMovies()
    }

    private fun setupRecyclerView() {
        recyclerView.setupMergeAdapter(searchBarAdapter)
    }

    private fun observeMovies() {
        val rvAdapter = recyclerView.adapter as MergeAdapter

        nowPlayingViewModel.nowPlayingMovies.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(nowPlayingLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(nowPlayingLoadingAdapter)
                    rvAdapter.addAdapter(1, nowPlayingAdapter)

                    nowPlayingMovies.addAll(it.data?.results?.take(5) ?: emptyList())
                    nowPlayingAdapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {
                    rvAdapter.removeAdapter(nowPlayingLoadingAdapter)
                }
            }
        })

        topRatedViewModel.topRatedMovies.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(topRatedLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(topRatedLoadingAdapter)
                    rvAdapter.addAdapter(2, topRatedAdapter)

                    topRatedMovies.addAll(it.data?.results?.take(5) ?: emptyList())
                    topRatedAdapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {
                    rvAdapter.removeAdapter(topRatedLoadingAdapter)
                }
            }
        })

        popularViewModel.popularMovies.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(popularLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(popularLoadingAdapter)
                    rvAdapter.addAdapter(3, popularAdapter)

                    popularMovies.addAll(it.data?.results?.take(5) ?: emptyList())
                    popularAdapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {
                    rvAdapter.removeAdapter(popularLoadingAdapter)
                }
            }
        })
    }

    override fun onBackPressed() {
        activity?.finishAndRemoveTask()
    }
}