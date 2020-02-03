package com.xdwin.home

import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.ext.setupGridAdapter
import com.xdwin.data.data.Movie
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getContentView() = R.layout.fragment_home

    private lateinit var model: HomeViewModel
    private lateinit var useCase: HomeUseCase
    private lateinit var repository: HomeRepository

    private var movies = mutableListOf<Movie>()
    private val onMovieClickListener: ((Movie) -> Unit) = {
        val uri = "detail://detail".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.putExtra(Constants.INTENT_MOVIE_ID_KEY, it.id)
        startActivity(intent)
    }
    private val adapter: HomeAdapter by lazy {
        HomeAdapter(
            movies,
            onMovieClickListener
        )
    }

    override fun initDependency() {
        repository = HomeRepository()
        useCase = HomeUseCase(repository)
        model = ViewModelProviders.of(this, HomeViewModelFactory(useCase))
            .get(HomeViewModel::class.java)
    }

    override fun initView() {
        setupRecyclerView()
        observeMovies()
    }

    private fun setupRecyclerView() {
        recyclerView.setupGridAdapter(adapter, 2)
    }

    private fun observeMovies() {
        model.popularMovies.observe(this, Observer {
            if (it.isSuccessful) {
                movies.addAll(movies + (it.body()?.results ?: listOf()))
                adapter.notifyDataSetChanged()
            } else {
                Log.e("result", it.message())
            }
        })
    }
}