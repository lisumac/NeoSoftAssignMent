package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity()
@Parcelize
data class AggregateRating(
   // val @type: String,
  /*  @PrimaryKey(autoGenerate = true)
    private var aggregatePrimaryKey: Int? = null,*/
    val bestRating: String,
    val ratingCount: Int,
    val ratingValue: Double,
    val worstRating: String
):Parcelable