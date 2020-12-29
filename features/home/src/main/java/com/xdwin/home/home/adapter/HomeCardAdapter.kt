package com.xdwin.home.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.URLS.BASE_IMAGE_MOVIEDB_500
import com.xdwin.data.data.Movie
import com.xdwin.home.R

class HomeCardAdapter(
    val data: List<Movie>,
    val listener: (Movie) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<Movie>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_card, parent, false)
        return ViewHolder(view, 0)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        holder.bind(data[position], listener)
    }

    class ViewHolder(view: View, tag: Int) : BaseViewHolder<Movie>(view, tag) {
        val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        val tvMovieTitle = view.findViewById<TextView>(R.id.tvMovieTitle)

        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            tvMovieTitle.text = data.originalTitle
            Glide.with(ivMovieImage.context)
                .load(BASE_IMAGE_MOVIEDB_500 + data.posterPath)
                .into(ivMovieImage)
            ivMovieImage.setOnClickListener {
                onClickListener(data)
            }
        }
    }
}