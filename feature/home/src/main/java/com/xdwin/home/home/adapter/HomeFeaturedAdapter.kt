package com.xdwin.home.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.URLS
import com.xdwin.data.data.Movie
import com.xdwin.home.R
import kotlinx.android.synthetic.main.item_home_featured.view.*

class HomeFeaturedAdapter(
    val movie: Movie,
    val onMovieClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<HomeFeaturedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_featured, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movie, onMovieClickListener)
    }

    class ViewHolder(val view: View) : BaseViewHolder<Movie>(view, 0) {
        val featuredImage = view.ivFeaturedImage
        val featuredTitle = view.tvFeaturedTitle
        val featuredRuntime = view.tvFeaturedRuntime
        val featuredReviewCount = view.tvFeaturedReviewCount
        val featuredRating = view.rbFeaturedRatingBar
        val featuredDescription = view.tvFeaturedDescription

        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            view.setOnClickListener { onClickListener(data) }
            Glide.with(featuredImage)
                .load(URLS.BASE_IMAGE_MOVIEDB_500 + data.posterPath)
                .into(featuredImage)
            featuredTitle.text = data.title
            featuredRuntime.text = "99 Minutes" // todo @xdwin
            featuredReviewCount.text = "${data.voteCount} Reviews"
            featuredRating.rating = (data.voteAverage?.toFloat() ?: 0f) / 2f
            featuredDescription.text = "${data.overview?.substring(0, 100)}..."
        }
    }
}