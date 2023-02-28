package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityMainBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnItemOnClickListner
import com.assignment.neosoftassignment.pagination.MainLoadStateAdapter
import com.assignment.neosoftassignment.view.adapter.MovieListAdapter
import com.assignment.neosoftassignment.view.adapter.MoviesPagedListAdapter
import com.assignment.neosoftassignment.viewModel.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemOnClickListner {
    lateinit var binding: ActivityMainBinding
    var movieList: ArrayList<MovieResponseItem> = ArrayList()
    val viewModel: DashBoardViewModel by viewModels()
    private var movies: PagedList<MovieResponseItem>? = null
    lateinit var adapter: MovieListAdapter
    lateinit var manager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }

        inIt()
        setUpRecyclerview()
        onClicklistner()
        onSrollView()


    }

    private fun onSrollView() {


    }


    private fun setUpRecyclerview() {
        binding.progressBar.progress = 8
        viewModel.searchdata("")
        manager = LinearLayoutManager(this)
        binding.rvMovieList.layoutManager = manager
        /* viewModel.movieListLiveData.observe(this) { it ->
             Log.e("TAG", "setUpRecyclerview: $it")
             movieList = it as ArrayList<MovieResponseItem>
             adapter = MovieListAdapter(it)
             binding.rvMovieList.adapter = adapter
             adapter.onItemOnClickListner = this


         }*/
       val moviesAdapter = MoviesPagedListAdapter()
        moviesAdapter.onItemOnClickListner =this
        binding.rvMovieList.adapter = moviesAdapter







        binding.rvMovieList.adapter = moviesAdapter!!.withLoadStateFooter(
            MainLoadStateAdapter()
        )

        lifecycleScope.launch {
           /* viewModel.getpagedata.collectLatest {
                moviesAdapter.submitData(it)
            }*/
            Log.e("TAG", "setUpRecyclerview: ", )
            viewModel.flowsearch.collectLatest {
                moviesAdapter.submitData(it)
            }

        }

    }

    private fun onClicklistner() {

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                viewModel.searchdata(editable.toString().trim())
               // adapter.filterList(movieList)

            }
        })

        binding.ivLikeMovieList.setOnClickListener {
            val intent = Intent(this, FavouriteMovieListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun inIt() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.dashboardVm = viewModel
        binding.progressBar.progress = 0
    }

    override fun onItemClickListener(product: MovieResponseItem, position: Int) {
        Log.e("TAG", "onItemClickListener:fav $product")
        viewModel.addToFavList(product.isFavourite, product.movieResponsePrimaryKey)
    }


}