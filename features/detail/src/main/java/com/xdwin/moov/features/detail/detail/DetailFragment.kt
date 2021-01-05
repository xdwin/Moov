package com.xdwin.moov.features.detail.detail

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.View.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.xdwin.abstraction.Constants
import com.xdwin.abstraction.Randomizer
import com.xdwin.abstraction.abstraction.BaseFragment
import com.xdwin.abstraction.ext.dp
import com.xdwin.abstraction.ext.setupHorizontalAdapter
import com.xdwin.data.URLS
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Cast
import com.xdwin.data.data.Genre
import com.xdwin.data.data.MovieDetail
import com.xdwin.moov.features.detail.R
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.get

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    // model
    val model: DetailViewModel = get()

    // data
    private var movieId: Int = -1
    private var uiData = UIData()

    // adapter
    private val castAdapter by lazy {
        CastAndCrewAdapter(uiData.casts)
    }

    override fun initDependency() {}

    override fun initView() {
        val intent = activity?.intent
        intent?.run { 
            movieId = getIntExtra(Constants.INTENT_MOVIE_ID_KEY, -1)
        }
        setupRecyclerView()
        observeMovie()
        observeCredits()
    }

    private fun setupRecyclerView() {
        rvCasts.setupHorizontalAdapter(castAdapter)
    }

    private fun observeMovie() {
        if (movieId != -1) {
            model.fetchPopularMovies(movieId)
        }
        model.detailMovie.observe(this, Observer {
            when(it) {
                is BaseResult.Loading -> {}
                is BaseResult.Success -> {
                    uiData.apply { movieDetail = it.data }.also(::renderLayout)
                }
                is BaseResult.Error -> {
                    Toast.makeText(this.context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeCredits() {
        if (movieId != -1) {
            model.fetchCredits(movieId)
        }
        model.credits.observe(this, Observer {
            when(it) {
                is BaseResult.Success -> {
                    uiData.apply {
                        casts.addAll(it.data?.cast?.take(5) ?: emptyList())
                    }.also(::renderLayout)
                }
                is BaseResult.Error -> {
                    Toast.makeText(this.context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderLayout(uiData: UIData) {
        with(uiData) {
            ivBack.setOnClickListener { activity?.finish() }
            tvAdult.visibility = if (movieDetail?.adult == true) VISIBLE else INVISIBLE
            ivHeader.run {
                Glide.with(this)
                    .load(URLS.BASE_IMAGE_MOVIEDB_1280 + movieDetail?.backdropPath)
                    .into(this)
            }
            movieDetail?.run {
                tvTitle.text = title
                tvRuntime.text = "$runtime Minutes"
                tvReviewCount.text = "$voteCount Reviews"
                rbRatingBar.rating = (voteAverage.toFloat() / 2f)
                llGenreContent.run {
                    genres.map { createGenreItem(it) }.forEach(::addView)
                }
                tvSummaryContent.text = overview
            }

            if (casts.isNotEmpty()) {
                tvCasts.visibility = VISIBLE
                rvCasts.visibility = VISIBLE
                castAdapter.notifyDataSetChanged()
            } else {
                tvCasts.visibility = GONE
                rvCasts.visibility = GONE
            }
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

    private data class UIData(
        var movieDetail: MovieDetail? = null,
        var casts: MutableList<Cast> = mutableListOf()
    )
}