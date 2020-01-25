package com.xdwin.base.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xdwin.base.R
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.base.ext.setupGridAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getContentView() = R.layout.fragment_home

    private lateinit var model: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun initDependency() {
        model = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun initView() {
        setupRecyclerView()
        observeMovies()
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(model.getPopularMovies().value?.results ?: emptyList())
        recyclerView.setupGridAdapter(adapter, 2)
    }

    private fun observeMovies() {
        model.getPopularMovies().observe(this, Observer {
            Log.e("result", it.results.toString())
            adapter.data = it.results
            adapter.notifyDataSetChanged()
        })
    }
}