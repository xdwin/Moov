package com.xdwin.detail.detail

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.Randomizer
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.dagger.ViewModelFactory
import com.xdwin.abstraction.ext.dp
import com.xdwin.data.URLS
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Genre
import com.xdwin.data.data.Movie
import com.xdwin.data.data.MovieDetail
import com.xdwin.detail.R
import com.xdwin.detail.dagger.DetailComponent
import com.xdwin.detail.dagger.DetailComponentCreator
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject
import kotlin.random.Random

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    lateinit var detailComponent: DetailComponent
    private val random by lazy { Random(5) }

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
            movieId = getIntExtra(Constants.INTENT_MOVIE_ID_KEY, -1)
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
        ivBack.setOnClickListener { activity?.finish() }
        tvAdult.visibility = if (movie.adult) VISIBLE else INVISIBLE
        ivHeader.run {
            Glide.with(this)
                .load(URLS.BASE_IMAGE_MOVIEDB_1280 + movie.backdropPath)
                .into(this)
        }
        movie.run {
            tvTitle.text = title
            tvRuntime.text = "$runtime Minutes"
            llGenreContent.run {
                genres.map { createGenreItem(it) }.forEach(::addView)
            }
            tvSummaryContent.text = overview
        }
    }

    private fun createGenreItem(genre: Genre): View {
        return TextView(view?.context).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).also {
                it.marginEnd = 4.dp
            }
            text = genre.name
            setTextColor(ContextCompat.getColor(context, R.color.white))
            background = ColorDrawable(ContextCompat.getColor(context, randomizeColor()))
        }
    }

    private fun randomizeColor(): Int {
        val colorList = listOf(
            R.color.blue,
            R.color.sea,
            R.color.red,
            R.color.green,
            R.color.ginger
        )
        return colorList.get(Randomizer.generateNextInt(0, 4))
    }
}