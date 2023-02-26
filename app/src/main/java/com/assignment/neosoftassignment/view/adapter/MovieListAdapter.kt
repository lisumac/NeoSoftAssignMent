package com.assignment.neosoftassignment.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.databinding.MovieItemListLayoutBinding
import com.assignment.neosoftassignment.model.responseModel.MovieResponse

class MovieListAdapter(var movieList: ArrayList<MovieResponseItem>) :
    RecyclerView.Adapter<MovieListAdapter.PersonDetailsViewHolder>() {
   // lateinit var onItemOnClickListner: ItemOnClickListner
    private var filteredProductNameList: List<MovieResponseItem>? = null

    class PersonDetailsViewHolder(private val binding: MovieItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieResponseItem) {


            binding.apply {

              tvProductName.text = movie.name
                tvProductDetails.text = movie.genre[0]
                tvProductBrandName.text = movie.description
              // tvOfferPrice.text = movie.datePublished

                //tvOriginalPrice.text = "\u20B9" + product.price;
                ivProduct.load(movie.image) {
                    crossfade(true)
                    crossfade(1000)
                }
               /* Glide.with(ivProduct.context)
                    .load(movie.image)
                    .into(ivProduct)*/

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {

        val binding =
            MovieItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
        val product = movieList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener { mview ->

            /* val action = ProductListFragmentDirections.actionProductListToDetailFragment(product)

             mview.findNavController().navigate(action)*/
            //onItemOnClickListner.itemClick(product)
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun sortedList(movie_list: ArrayList<MovieResponseItem>) {

        this.movieList = movie_list
        notifyDataSetChanged()
    }


    fun filterList(filterdNames: ArrayList<MovieResponseItem>) {

        this.movieList = filterdNames;
        notifyDataSetChanged();
    }
}