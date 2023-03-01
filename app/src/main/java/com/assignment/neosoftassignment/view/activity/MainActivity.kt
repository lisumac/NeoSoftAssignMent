package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingData
import com.assignment.neosoftassignment.Constants
import com.assignment.neosoftassignment.Constants.currentuserInterest
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityMainBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnClickListner
import com.assignment.neosoftassignment.onClickListner.OnItemOnClickListner
import com.assignment.neosoftassignment.view.adapter.MovieListAdapter
import com.assignment.neosoftassignment.view.bottomSheet.SortingBottomSheetDialog
import com.assignment.neosoftassignment.viewModel.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemOnClickListner, OnClickListner {
    lateinit var binding: ActivityMainBinding
    var movieList: ArrayList<MovieResponseItem> = ArrayList()
    val viewModel: DashBoardViewModel by viewModels()
    lateinit var list_Movies: Flow<PagingData<MovieResponseItem>>
    lateinit var bottomSheetDialog: SortingBottomSheetDialog

    /*lateinit var moviesAdapter: MoviesPagedListAdapter*/
    lateinit var moviesAdapter: MovieListAdapter

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


        /*binding.rvMovieList.adapter = moviesAdapter!!.withLoadStateFooter(
            MainLoadStateAdapter()
        )*/


        /* lifecycleScope.launch {



             viewModel.list_Movies.collectLatest {
                 if (issearchClicked) {

                     moviesAdapter.submitData(lifecycle, PagingData.empty())

                 } else {
                     moviesAdapter.submitData(it)
                     moviesAdapter.notifyDataSetChanged()
                     binding.rvMovieList.invalidate()
                 }
             }

         }*/

        viewModel.getUserList?.observe(this) { it ->
            it.forEach {
                if (it.email == Constants.currentUserName) {
                    Constants.currentUserPrimaryKey = it.userId
                    currentuserInterest = it.movie_interest
                }

            }
        }
        val map: HashMap<Int, MovieResponseItem> = HashMap()
        viewModel.movieListLiveData.observe(this) { it ->

            // movieList = it as ArrayList<MovieResponseItem>
            if (currentuserInterest.isEmpty()) {
                populateList(it as ArrayList<MovieResponseItem>)
            }else{
                it.forEach {
                    it.genre.forEach { itg ->
                        currentuserInterest.forEach { inte ->
                            if (inte == itg) {
                                map[it.movieResponsePrimaryKey] = it

                                Log.e("TAG", "setUpRecyclerview: $it")
                            }
                        }
                    }
                    val values: Collection<MovieResponseItem> = map.values
                    movieList = ArrayList(values)
                    populateList(movieList)


                }
            }



        }




        viewModel.searchedList?.observe(this) {
            Log.e("TAG", "setUpRecyclerview: " + it as ArrayList<MovieResponseItem>)
            moviesAdapter.filterList(it)


        }

        viewModel.movieListASCList?.observe(this) {
            binding.progressBar.visibility = View.GONE
            moviesAdapter.sortedList(it as ArrayList<MovieResponseItem>)
        }


    }

    private fun populateList(movieList: ArrayList<MovieResponseItem>) {
        binding.progressBar.visibility = View.GONE
        moviesAdapter = MovieListAdapter(movieList)
        binding.rvMovieList.adapter = moviesAdapter
        moviesAdapter.onItemOnClickListner = this
    }


    private fun onClicklistner() {

        binding.tvSortItem.setOnClickListener {
            bottomSheetDialog = SortingBottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, "TAG")
            bottomSheetDialog.getHandlerData_ = this


        }
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                Log.e("TAG", "afterTextChanged: " + editable.toString().trim())



                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getSearchList(editable.toString().trim())

                }


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
        binding.progressBar.visibility = View.VISIBLE

    }

    override fun onItemClickListener(product: MovieResponseItem, position: Int) {
        Log.e("TAG", "onItemClickListener: "+product.genre )
        viewModel.addToFavList(product.isFavourite, product.movieResponsePrimaryKey, product.genre)
    }

    override fun getAscendingOrder() {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {

            Log.e("TAG", "onClicklistner:Asc ")
            viewModel.getAscOrder(true)
            bottomSheetDialog.dismiss()

        }
    }

    override fun getDescendingOrder() {
        binding.progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            Log.e("TAG", "onClicklistner:c;DSC ")
            viewModel.getAscOrder(false)
            bottomSheetDialog.dismiss()

        }
    }


}