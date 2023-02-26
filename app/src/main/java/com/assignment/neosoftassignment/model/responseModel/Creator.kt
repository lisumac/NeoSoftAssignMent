package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Entity()/*foreignKeys = [
    ForeignKey(
        entity = MovieResponseItem::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("creatorPrimaryKey")
    )
])*/
@Parcelize
data class Creator(
    @SerializedName("@type")
    val type: String,
   /* @PrimaryKey(autoGenerate = true)
    private var creatorPrimaryKey: Int,*/
    var name: String = "",
    var url: String
): Parcelable{
    companion object {
        operator fun invoke(
            type: String? = null, name: String? = null, url: String) = Creator(type ?: "", name ?: "",url?:"")
    }
}