package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = MovieResponseItem::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("directorPrimaryKey")
        )
    ]
)
@Parcelize

data class Director(
    @SerializedName("@type")
    val type: String,
    @PrimaryKey(autoGenerate = true)
    private var directorPrimaryKey: Int,
    var name: String,
    var url: String,

): Parcelable