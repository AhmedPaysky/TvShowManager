package com.osama.tvshowmanager.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osama.tvshowmanager.domain.models.SingleMovieModel
import com.osama.tvshowmanager.databinding.ItemMovieBinding
import com.tvshowmanager.server.MoviesQuery

class MoviesAdapter(
    private val list: MoviesQuery.Movies,
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list, position)
    }

    override fun getItemCount() = list.edges?.size!!

    inner class ViewHolder(private val view: ItemMovieBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(singleMovieModel: MoviesQuery.Movies, position: Int) {
            with(view) {
                singleMovieModel.apply {
                    tvTitle.text = singleMovieModel.edges?.get(position)?.node?.title
                    tvSeason.text = singleMovieModel.edges?.get(position)?.node?.seasons.toString()
                    tvDate.text =
                        singleMovieModel.edges?.get(position)?.node?.releaseDate.toString()
                }
            }
        }
    }
}