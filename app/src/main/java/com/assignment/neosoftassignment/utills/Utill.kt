package com.assignment.neosoftassignment.utills

import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import java.util.*


object Utill {

    /*private fun saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences =SharedPreferences("shared preferences", MODE_PRIVATE)

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences.edit()

        // creating a new variable for gson.
        val gson = Gson()

        // getting data from gson and storing it in a string.
        val json = gson.toJson(courseModalArrayList)

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("courses", json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply()

        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }*/
     fun filterList(
        text: String,
        movieList: ArrayList<MovieResponseItem>
    ): ArrayList<MovieResponseItem> {
        /* new array list that will hold the filtered data */
        val filterdNames: ArrayList<MovieResponseItem> = ArrayList()
       /* for (s in movieList) {
            if (s.name.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                filterdNames.add(s)
            }
        }
*/
        return filterdNames
    }
}