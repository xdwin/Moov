package com.xdwin.base.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xdwin.base.R
import com.xdwin.base.ext.setupHorizontalAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var model: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setupRecyclerView()
        observeMovies()
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(model.getPopularMovies().value?.results ?: emptyList())
        recyclerView.setupHorizontalAdapter(adapter)
    }

    private fun observeMovies() {
        model.getPopularMovies().observe(this, Observer {
            Log.e("result", it.results.toString())
            adapter.data = it.results
            adapter.notifyDataSetChanged()
        })
    }
}