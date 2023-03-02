package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import coil.size.Scale
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityMovieDetailsBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.onClickListner.OnClickListnerLogin
import com.assignment.neosoftassignment.viewModel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity(), OnClickListnerLogin {
    val viewModel: MovieDetailsViewModel by viewModels()
    var favMovieList: ArrayList<MovieResponseItem> = ArrayList()

    lateinit var binding: ActivityMovieDetailsBinding
    var movieDetailsPrimaryKey: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        inIt()
        checkDataFromIntent()
        getdata()
        onClickListner()
        papulateUI()
    }

    private fun onClickListner() {
        binding.imgMovieFavorite.setOnClickListener {
            if (viewModel.movieResponseItems?.isFavourite == true) {
                viewModel.movieResponseItems?.isFavourite = false
                binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.removeORaddToFavList(
                    viewModel.movieResponseItems!!.isFavourite, movieDetailsPrimaryKey
                )
            } else {
                viewModel.movieResponseItems?.isFavourite = true
                binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.removeORaddToFavList(
                    viewModel.movieResponseItems!!.isFavourite, movieDetailsPrimaryKey
                )


            }
        }

    }

    private fun getdata() {
        viewModel.movieListLiveData.observe(this) { it ->

            favMovieList = it as ArrayList<MovieResponseItem>

        }
    }

    private fun papulateUI() {
        binding.prgBarMovies.visibility = View.GONE

        CoroutineScope(Dispatchers.Main).launch {
            delay(100)
            Log.e("TAG", "papulateUIView:" + viewModel.movieResponseItems!!.name)

            binding.apply {


                imgMovie.load(viewModel.movieResponseItems?.image) {
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }

                if (viewModel.movieResponseItems!!.isFavourite) {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.imgMovieFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                }

                tvMovieTitle.text = viewModel.movieResponseItems?.name
                tvMovieTagLine.text = viewModel.movieResponseItems?.keywords
                tvMovieDateRelease.text = viewModel.movieResponseItems?.datePublished
                tvMovieRating.text = viewModel.movieResponseItems?.contentRating
                tvMovieRuntime.text = viewModel.movieResponseItems?.duration
                Log.e("TAG", "papulateUIView:" + viewModel.movieResponseItems)

            }
        }
    }

    fun checkDataFromIntent() {

        Log.e("MovieDetails", "checkDataFromIntent: ")
        movieDetailsPrimaryKey = (intent.getIntExtra("MOVE_DETAILS", 0) as Int?)!!

        viewModel.getMovieDetailsWithPrimaryKey(movieDetailsPrimaryKey)


    }

    private fun inIt() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.movieDetailsVM = viewModel
        binding.lifecycleOwner = this
        binding.handler = this
        binding.prgBarMovies.visibility = View.VISIBLE

    }

    override fun registrationButtonOnclick() {

    }

    override fun loginButtonButtonOnclick() {
        Log.e("TAG", "loginButtonButtonOnclick: " +favMovieList.size)
        // viewModel.insertToDb(favMovieList)
       /* if (viewModel.allDataUpdated) {*/
            binding.prgBarMovies.visibility = View.GONE

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        //}
    }

    override fun login() {
    }

    override fun registration() {
    }


}


