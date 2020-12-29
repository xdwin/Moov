package com.xdwin.homelist

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

class HomeListAdapter(
    val data: List<Movie>,
    val onClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_homelist_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    class ViewHolder(val view: View, tag: Int = 0) : BaseViewHolder<Movie>(view, tag) {
        val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        val tvMovieTitle = view.findViewById<TextView>(R.id.tvMovieTitle)

        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            data.also {
                Glide.with(ivMovieImage)
                    .load(URLS.BASE_IMAGE_MOVIEDB_500 + it.posterPath)
                    .into(ivMovieImage)
                tvMovieTitle.text = it.originalTitle
                view.setOnClickListener{ onClickListener(data) }
            }
        }
    }
}