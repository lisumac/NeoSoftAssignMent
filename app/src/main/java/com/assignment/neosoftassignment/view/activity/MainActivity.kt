package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.filter
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemOnClickListner {
    lateinit var binding: ActivityMainBinding
    var movieList: ArrayList<MovieResponseItem> = ArrayList()
    val viewModel: DashBoardViewModel by viewModels()
    lateinit var manager: LinearLayoutManager
    var issearchClicked = false
    lateinit var list_Movies: Flow<PagingData<MovieResponseItem>>
    lateinit var moviesAdapter: MoviesPagedListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        Log.e("TAG", ":activity Created ")

        inIt()
        setUpRecyclerview()
        onClicklistner()


    }


    private fun setUpRecyclerview() {
        Log.e("TAG", ":activity setupRecylerView ")

        manager = LinearLayoutManager(this)
        binding.rvMovieList.layoutManager = manager
        moviesAdapter = MoviesPagedListAdapter()
        moviesAdapter.onItemOnClickListner = this
        binding.rvMovieList.adapter = moviesAdapter

        binding.rvMovieList.adapter = moviesAdapter!!.withLoadStateFooter(
            MainLoadStateAdapter()
        )

/* if (issearchClicked) {
                    Log.e("TAG", "setUpRecyclerview: 2")
                    moviesAdapter.submitData(lifecycle, PagingData.empty())

                } else {
                    Log.e("TAG", "setUpRecyclerview: 11")*/

        lifecycleScope.launch {
            Log.e("TAG", "setUpRecyclerview: 1")


            viewModel.list_Movies.collectLatest {
                if (issearchClicked) {
                    Log.e("TAG", "setUpRecyclerview: 2")
                    moviesAdapter.submitData(lifecycle, PagingData.empty())

                } else {
                    moviesAdapter.submitData(it)
                    moviesAdapter.notifyDataSetChanged()
                    binding.rvMovieList.invalidate()
                }
            }

        }

    }


    private fun onClicklistner() {
        binding.searchView.setOnClickListener {


        }
        binding.tvSortItem.setOnClickListener {
            Log.e("TAG", "onClicklistner: ")
            issearchClicked = true
            /*CoroutineScope(Dispatchers.IO).launch {
                viewModel.searchdata("The Kid")
            }*/
            viewModel.getSearchList()

            //moviesAdapter.submitData(lifecycle, PagingData.empty())
        }

        /* binding.searchView.addTextChangedListener(object : TextWatcher {
             override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
             override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

             }

             override fun afterTextChanged(editable: Editable) {
                 Log.e("TAG", "afterTextChanged: " + editable.toString().trim())

                     issearchClicked = true
                     Log.e("TAG", "afterTextChanged:1 ", )
                     viewModel.searchdata(editable.toString().trim())

                 moviesAdapter.submitData(lifecycle, PagingData.empty())

             }
         })*/

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
        Log.e("TAG", ":activity init ")

    }

    override fun onItemClickListener(product: MovieResponseItem, position: Int) {
        Log.e("TAG", "onItemClickListener:fav $product")
        viewModel.addToFavList(product.isFavourite, product.movieResponsePrimaryKey)
    }


}