package com.xdwin.moov.features.home.home

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
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
import com.xdwin.moov.features.home.R
import com.xdwin.moov.features.home.home.adapter.HomeFeaturedAdapter
import com.xdwin.moov.features.home.home.adapter.HomeCardLoadingAdapter
import com.xdwin.moov.features.home.home.adapter.HomeCardSectionAdapter
import com.xdwin.moov.features.home.home.adapter.HomeFeaturedLoadingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home),
    BackPressedListener {

    lateinit var activitySwitchListener: HomeSwitchFragmentListener

    private val nowPlayingViewModel: NowPlayingViewModel by viewModels()
    private val popularViewModel: PopularViewModel by viewModels()
    private val topRatedViewModel: TopRatedViewModel by viewModels()

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

    private val homeFeaturedLoadingAdapter: HomeFeaturedLoadingAdapter by lazy {
        HomeFeaturedLoadingAdapter()
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
        if (context is BaseActivity) {
            context.onBackPressedListener = this
        }
        if (context is HomeSwitchFragmentListener) {
            activitySwitchListener = context
        }
        super.onAttach(context)
    }

    override fun initDependency() {}

    override suspend fun initView() {
        setupRecyclerView()
        observeMovies()
        fetchMovies()
    }

    private fun setupRecyclerView() {
        recyclerView.setupMergeAdapter(searchBarAdapter)
    }

    private fun fetchMovies() {
        val nowPlayingAsync = async(Dispatchers.IO) { nowPlayingViewModel.fetchData() }
        val topRatedAsync = async(Dispatchers.IO) { topRatedViewModel.fetchData() }
        val popularAsync = async(Dispatchers.IO) { popularViewModel.fetchData() }

        launch(Dispatchers.IO) {
            nowPlayingAsync.await()
            topRatedAsync.await()
            popularAsync.await()
        }
    }

    private fun observeMovies() {
        val rvAdapter = recyclerView.adapter as MergeAdapter
        nowPlayingViewModel.observeData().observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(nowPlayingLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(nowPlayingLoadingAdapter)
                    rvAdapter.addAdapter(NOW_PLAYING_ITEM_POSITION, nowPlayingAdapter)

                    nowPlayingMovies.addAll(it.data?.results?.take(5) ?: emptyList())
                    nowPlayingAdapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {
                    rvAdapter.removeAdapter(nowPlayingLoadingAdapter)
                }
            }
        })

        topRatedViewModel.observeData().observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(topRatedLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(topRatedLoadingAdapter)
                    rvAdapter.addAdapter(TOP_RATED_ITEM_POSITION, topRatedAdapter)

                    topRatedMovies.addAll(it.data?.results?.take(5) ?: emptyList())
                    topRatedAdapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {
                    rvAdapter.removeAdapter(topRatedLoadingAdapter)
                }
            }
        })

        popularViewModel.observeData().observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {
                    rvAdapter.addAdapter(HOME_FEATURED_POSITION, homeFeaturedLoadingAdapter)
                    rvAdapter.addAdapter(popularLoadingAdapter)
                }
                is BaseResult.Success -> {
                    rvAdapter.removeAdapter(popularLoadingAdapter)
                    rvAdapter.removeAdapter(homeFeaturedLoadingAdapter)

                    rvAdapter.addAdapter(POPULAR_ITEM_POSITION, popularAdapter)
                    val featuredAdapter = HomeFeaturedAdapter(
                        it.data?.results?.get(0)!!,
                        onMovieClickListener
                    )
                    rvAdapter.addAdapter(HOME_FEATURED_POSITION, featuredAdapter)

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

    companion object {
        const val HOME_FEATURED_POSITION = 1
        const val POPULAR_ITEM_POSITION = 2
        const val NOW_PLAYING_ITEM_POSITION = 3
        const val TOP_RATED_ITEM_POSITION = 4
    }
}