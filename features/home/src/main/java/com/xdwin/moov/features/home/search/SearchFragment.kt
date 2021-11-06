package com.xdwin.moov.features.home.search

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.abstraction.BaseActivity
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.ext.setupVerticalAdapter
import com.xdwin.abstraction.ext.showSoftKeyboard
import com.xdwin.abstraction.listener.BackPressedListener
import com.xdwin.abstraction.listener.EndlessRecyclerScrollListener
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.moov.features.home.R
import com.xdwin.moov.features.home.home.HomeSwitchFragmentListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_search_section.*

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search),
    BackPressedListener {

    lateinit var activitySwitchListener: HomeSwitchFragmentListener
    private val searchViewModel: SearchViewModel by viewModels()

    private var movies = mutableListOf<Movie>()
    private val onMovieClickListener: ((Movie) -> Unit) = {
        val uri = "moov://detail".toUri()
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
        observeSearchResult()
        setupSearchBar()
    }

    private fun setupRecyclerView() {
        with(recyclerView) {
            setupVerticalAdapter(this@SearchFragment.adapter)
            addOnScrollListener(EndlessRecyclerScrollListener(true) {
                onLoadMore()
            })
        }
    }

    private fun setupSearchBar() {
        with(etSearch) {
            visibility = View.VISIBLE
            showSoftKeyboard()
            addTextChangedListener(searchTextWatcher)
        }
        ivRightIcon.setOnClickListener {
            if (!etSearch.editableText.isNullOrEmpty()) {
                etSearch.setText("")
                onTextChanged("")
            }
        }
    }

    override fun onBackPressed() {
        activitySwitchListener.switchToHomeFragment()
    }

    private fun onTextChanged(_query: String?) {
        _query?.run { searchViewModel.onFirstSearch(this) }
    }

    private fun onLoadMore() {
        searchViewModel.onLoadMore()
    }

    private fun observeSearchResult() {
        searchViewModel.searchResult.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {}
                is BaseResult.Success -> {
                    if (searchViewModel.page == SearchViewModel.FIRST_PAGE) {
                        movies.clear()
                    }
                    movies.addAll(it.data?.results ?: emptyList())
                    adapter.notifyDataSetChanged()
                }
                is BaseResult.Error -> {}
            }
        })
    }

    private val searchTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (p0?.toString()?.isEmpty() == true || etSearch.editableText.isNullOrEmpty()) {
                ivRightIcon.setImageDrawable(context?.getDrawable(R.drawable.ic_search))
            } else {
                ivRightIcon.setImageDrawable(context?.getDrawable(R.drawable.ic_clear_circle))
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged(p0.toString())
        }
    }
}