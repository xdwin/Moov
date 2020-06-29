package com.xdwin.home.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.data.Movie

class BannerAdapter(val data: List<Movie>,
                    val listener: (Movie) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<Movie>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}