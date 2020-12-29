package com.xdwin.home.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.xdwin.home.R

class HomeCardLoadingAdapter : RecyclerView.Adapter<HomeCardLoadingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_homecard_loading, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shimmer = view.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        fun bind() {
            shimmer.startShimmer()
        }
    }
}