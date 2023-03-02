package com.assignment.neosoftassignment.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.MovieItemListLayoutBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnItemOnClickListner

class MovieListAdapter(var movieList: ArrayList<MovieResponseItem>) :
    RecyclerView.Adapter<MovieListAdapter.PersonDetailsViewHolder>() {
    lateinit var onItemOnClickListner: OnItemOnClickListner
    lateinit var binding: MovieItemListLayoutBinding

    class PersonDetailsViewHolder(private val binding: MovieItemListLayoutBinding) :
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

                /*if (movie.isFavourite) {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                }*/
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {

        binding =
            MovieItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
        val product = movieList[position]
        holder.bind(product, onItemOnClickListner, position)
        holder.itemView.setOnClickListener {
            onItemOnClickListner.navigateToMovieDetails(product,position)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun sortedList(movie_list: ArrayList<MovieResponseItem>) {

        movieList.clear()
        movieList.addAll(movie_list);
        notifyDataSetChanged()
    }


    fun filterList(filterdNames: ArrayList<MovieResponseItem>) {
        Log.e("TAG", "filterList: " + filterdNames.size)
        movieList.clear()
        movieList.addAll(filterdNames);
        Log.e("TAG", "filterList:movieList " + movieList.size)
        notifyDataSetChanged();
    }
}