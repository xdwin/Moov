package com.xdwin.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xdwin.data.URLS.BASE_IMAGE_MOVIEDB_500
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.data.Movie

class HomeAdapter(var data: List<Movie>, var onClickListener: ((Movie) -> Unit)) : RecyclerView.Adapter<BaseViewHolder<Movie>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return if (viewType == CARD_TITLE_UPPER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_card_upper_title, parent, false)
            CardTitleUpperViewHolder(
                view,
                CARD_TITLE_UPPER
            )
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_card_lower_title, parent, false)
            CardTitleLowerViewHolder(
                view,
                CART_TITLE_LOWER
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        when(holder.tag) {
            CARD_TITLE_UPPER -> {
                holder.bind(data.get(position), onClickListener)
            }

            CART_TITLE_LOWER -> {
                holder.bind(data.get(position), onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0) {
            CARD_TITLE_UPPER
        } else CART_TITLE_LOWER
    }

    class CardTitleUpperViewHolder(view: View, tag: Int) : BaseViewHolder<Movie>(view, tag) {
        var ivMovieCard = view.findViewById<ImageView>(R.id.ivMovieCard)
        var tvMovieCard = view.findViewById<TextView>(R.id.tvMovieCard)

        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            data.let {
                Glide.with(ivMovieCard)
                    .load(BASE_IMAGE_MOVIEDB_500 + it.posterPath)
                    .into(ivMovieCard)
                tvMovieCard.text = it.title
                itemView.setOnClickListener { onClickListener.invoke(data) }
            }
        }
    }

    class CardTitleLowerViewHolder(view: View, tag: Int) : BaseViewHolder<Movie>(view, tag) {
        var ivMovieCard = view.findViewById<ImageView>(R.id.ivMovieCard)
        var tvMovieCard = view.findViewById<TextView>(R.id.tvMovieCard)
        override fun bind(data: Movie, onClickListener: (Movie) -> Unit) {
            data.let {
                Glide.with(ivMovieCard)
                    .load(BASE_IMAGE_MOVIEDB_500 + it.posterPath)
                    .into(ivMovieCard)
                tvMovieCard.text = it.title
                itemView.setOnClickListener { onClickListener.invoke(data) }
            }
        }
    }

    companion object {
        private const val CARD_TITLE_UPPER = 1
        private const val CART_TITLE_LOWER = 2
    }
}

