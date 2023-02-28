package com.assignment.neosoftassignment.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.assignment.neosoftassignment.Constants.movieList
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.MovieItemListLayoutBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnItemOnClickListner
class MoviesPagedListAdapter: PagingDataAdapter<MovieResponseItem, MoviesPagedListAdapter.MoviesViewHolder>(DIFF_CALLBACK){
    lateinit var onItemOnClickListner: OnItemOnClickListner
    private var filteredProductNameList: List<MovieResponseItem>? = null
    lateinit var binding: MovieItemListLayoutBinding
    class MoviesViewHolder(private val binding: MovieItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            movie: MovieResponseItem,
            onItemOnClickListner: OnItemOnClickListner,
            position: Int
        ) {


            binding.apply {

                tvMovieName.text = movie.name
                tvMovieDateRelease.text = movie.datePublished
                tvRate.text = movie.contentRating
                tvLang.text = movie.genre[0]
                imgMovie.load(movie.image) {
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }

                if (movie.isFavourite) {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    onItemOnClickListner.onItemClickListener(movie, position)
                } else {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    onItemOnClickListner.onItemClickListener(movie, position)

                }
            }


            binding.imgMovieFavorite.setOnClickListener {
                if (!movie.isFavourite) {
                    movie.isFavourite = true
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    onItemOnClickListner.onItemClickListener(movie, position)
                } else {
                    movie.isFavourite = false
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    onItemOnClickListner.onItemClickListener(movie, position)

                }


            }


        }


    }

    private val itemCallback: DiffUtil.ItemCallback<MovieResponseItem> =
        object : DiffUtil.ItemCallback<MovieResponseItem>() {
            override fun areItemsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
                return oldItem.movieResponsePrimaryKey === newItem.movieResponsePrimaryKey
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResponseItem>() {
            override fun areItemsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
                return oldItem.movieResponsePrimaryKey == newItem.movieResponsePrimaryKey
            }

        }
    }
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val product = getItem(position)
        product?.let { holder.bind(it, onItemOnClickListner, position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = MovieItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }
}