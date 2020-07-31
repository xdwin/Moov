package com.xdwin.detail.detail

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.dagger.ViewModelFactory
import com.xdwin.data.URLS
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.MovieDetail
import com.xdwin.detail.R
import com.xdwin.detail.dagger.DetailComponent
import com.xdwin.detail.dagger.DetailComponentCreator
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    lateinit var detailComponent: DetailComponent

    @Inject
    lateinit var modelFactory: ViewModelFactory
    private lateinit var model: DetailViewModel

    private var movieId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailComponent = (context.applicationContext as DetailComponentCreator).createDetailComponent()
        detailComponent.inject(this)
    }

    override fun initDependency() {
        model = ViewModelProviders.of(this, modelFactory)
            .get(DetailViewModel::class.java)
    }

    override fun initView() {
        val intent = activity?.intent
        intent?.run { 
            movieId = getIntExtra(com.xdwin.abstraction.Constants.INTENT_MOVIE_ID_KEY, -1)
        }
        observeMovie()
    }

    private fun observeMovie() {
        if (movieId != -1) {
            model.fetchPopularMovies(movieId)
        }
        model.detailMovie.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {}
                is BaseResult.Success -> {
                    it.data?.run(::renderLayout)
                }
                is BaseResult.Error -> {
                    Toast.makeText(this.context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderLayout(movie: MovieDetail) {
        ivHeader.apply {
            Glide.with(this)
                .load(URLS.BASE_IMAGE_MOVIEDB_1280 + movie.backdropPath)
                .into(this)
        }
        movie.run {
            tvTitle.text = title
            tvRuntime.text = "$runtime Minutes"
            tvSummaryContent.text = overview
        }
    }
}