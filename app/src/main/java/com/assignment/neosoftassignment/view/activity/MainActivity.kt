package com.assignment.neosoftassignment.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityMainBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.utills.Utill.filterList
import com.assignment.neosoftassignment.view.adapter.MovieListAdapter
import com.assignment.neosoftassignment.viewModel.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var movieList: ArrayList<MovieResponseItem> = ArrayList()
    val viewModel: DashBoardViewModel by viewModels()
    lateinit var adapter: MovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inIt()
        setUpRecyclerview()
        onClicklistner()


    }

    private fun setUpRecyclerview() {
        viewModel.movieListLiveData.observe(this) { it ->

            Log.e("TAG", "setUpRecyclerview: $it")
            movieList = it as ArrayList<MovieResponseItem>
            adapter = MovieListAdapter(it as ArrayList<MovieResponseItem>)

            binding.rvMovieList.adapter = adapter
        }
    }

    private fun onClicklistner() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                // val list = filterList(editable.toString(),movieList)
              //  adapter.filterList(movieList)

            }
        })
    }

    private fun inIt() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.dashboardVm = viewModel

    }
}