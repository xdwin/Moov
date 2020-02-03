package com.xdwin.detail.detail

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.data.URLS
import com.xdwin.data.data.Movie
import com.xdwin.detail.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {

    private lateinit var model: DetailViewModel
    private lateinit var useCase: DetailUseCase
    private lateinit var repository: DetailRepository

    override fun getContentView() = R.layout.fragment_detail

    private var movieId: Int = -1
    
    override fun initDependency() {
        repository = DetailRepository()
        useCase = DetailUseCase(repository)
        model = ViewModelProviders.of(this, DetailViewModelFactory(useCase))
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
        model.popularMovies.observe(this, Observer {
            if (it.isSuccessful) {
                it.body()?.run(::renderLayout)
            } else {
                Toast.makeText(this.context, it.message(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun renderLayout(movie: Movie) {
        ivHeader.apply {
            Glide.with(this)
                .load(URLS.BASE_IMAGE_MOVIEDB_1280 + movie.backdropPath)
                .into(this)
        }
        ivPoster.apply {
            Glide.with(this)
                .load(URLS.BASE_IMAGE_MOVIEDB_500 + movie.posterPath)
                .into(this)
        }
        movie.run {
            tvTitle.text = title
            tvDate.text = releaseDate
            tvRating.text = popularity.toString()
            tvVote.text = voteCount.toString()
            tvDescription.text = overview
        }
    }
}