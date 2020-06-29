package com.xdwin.home.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.URLS
import com.xdwin.data.data.Movie
import com.xdwin.home.R

class SearchAdapter(
    val data: List<Movie>,
    val onClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    class ViewHolder(val view: View) : BaseViewHolder<Movie>(view, tag = 0) {
        val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        val tvMovieTitle = view.findViewById<TextView>(R.id.tvMovieTitle)
        val tvMovieRating = view.findViewById<TextView>(R.id.tvMovieRating)
        val tvMovieDate = view.findViewById<TextView>(R.id.tvMovieDate)

        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            data.let {
                Glide.with(ivMovieImage)
                    .load(URLS.BASE_IMAGE_MOVIEDB_500 + it.posterPath)
                    .into(ivMovieImage)
                tvMovieTitle.setText(it.title)
                tvMovieRating.setText(it.voteAverage.toString())
                tvMovieDate.setText(it.releaseDate)
                view.setOnClickListener { onClickListener(data) }
            }
        }
    }
}