package com.assignment.neosoftassignment.view.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityFavouriteMovieListBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnItemOnClickListner
import com.assignment.neosoftassignment.view.adapter.MovieListAdapter
import com.assignment.neosoftassignment.viewModel.FavouriteMovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMovieListActivity : AppCompatActivity(), OnItemOnClickListner {
    lateinit var binding: ActivityFavouriteMovieListBinding
    var favMovieList: ArrayList<MovieResponseItem> = ArrayList()

    val viewModel: FavouriteMovieListViewModel by viewModels()
    lateinit var adapter: MovieListAdapter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        initBindingAndVm()
        setUpRecyclerview()


    }

    private fun setUpRecyclerview() {
        viewModel.movieListLiveData.observe(this) { it ->
            it.forEach {
                it.isFavourite
                if (it.isFavourite) {
                    binding.prgBarMoviesFav.visibility = View.GONE
                    favMovieList.add(it)
                    adapter = MovieListAdapter(favMovieList)
                    binding.rlMovies.adapter = adapter
                    adapter.onItemOnClickListner = this
                }
            }


        }
    }


    private fun initBindingAndVm() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite_movie_list)
        viewModel.also { binding.favouriteVm = it }
        binding.prgBarMoviesFav.visibility = View.VISIBLE

    }

    override fun onItemClickListener(product: MovieResponseItem, position: Int) {
        viewModel.removeFromFavList(product.isFavourite, product.movieResponsePrimaryKey)

    }


}