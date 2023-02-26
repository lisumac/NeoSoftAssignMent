package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
/*@Entity( foreignKeys = [
    ForeignKey(
        entity = MovieResponseItem::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("actorPrimaryKey")
    )
])*/
@Parcelize
data class Actor(
   // val @type: String,
   /* @PrimaryKey(autoGenerate = true)
    private var actorPrimaryKey: Int,*/
    val name: String,
    val url: String
):Parcelable