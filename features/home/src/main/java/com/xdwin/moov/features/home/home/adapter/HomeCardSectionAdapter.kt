package com.xdwin.moov.features.home.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xdwin.abstraction.ext.setupHorizontalAdapter
import com.xdwin.data.data.Movie
import com.xdwin.moov.features.home.R

class HomeCardSectionAdapter(
    val title: String,
    val data: List<Movie>,
    val listener: (Movie) -> Unit,
    val seeAllListener: () -> Unit
) : RecyclerView.Adapter<HomeCardSectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_card_section, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(title, data, listener, seeAllListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvSection)
        val tvSectionTitle = view.findViewById<TextView>(R.id.tvSectionTitle)
        val tvSectionSeeAll = view.findViewById<TextView>(R.id.tvSectionSeeAll)
        fun bind(
            title: String,
            data: List<Movie>,
            onClickListener: (Movie) -> Unit,
            seeAllListener: () -> Unit
        ) {
            tvSectionTitle.text = title
            tvSectionSeeAll.run {
                text = "See All Featured"
                setOnClickListener { seeAllListener() }
            }

            val adapter =
                HomeCardAdapter(
                    data,
                    onClickListener
                )
            recyclerView.setupHorizontalAdapter(adapter)
        }
    }
}