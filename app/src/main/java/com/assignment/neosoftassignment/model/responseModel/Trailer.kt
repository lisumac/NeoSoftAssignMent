package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverters
import com.assignment.neosoftassignment.model.roomdataBase.converters.ThumbnailConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*@Entity( foreignKeys = [
    ForeignKey(
        entity = MovieResponseItem::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("trailerPrimaryKey")
    )
])*/
@Entity
@Parcelize
data class Trailer(
    //val @type: String,
    /* @PrimaryKey(autoGenerate = true)
     private var trailerPrimaryKey: Int? = null,*/
    @SerializedName("@type")
    val type: String,
    val description: String = "",
    val embedUrl: String = "",
    val name: String = "",
    @TypeConverters(ThumbnailConverter::class)
    val thumbnail: Thumbnail,
    val thumbnailUrl: String = "",
    val uploadDate: String = ""
) : Parcelable {
    companion object {
        operator fun invoke(
            type: String? =null,
            description: String? = null,
            embedUrl: String? = null,
            name: String?,
            thumbnail: Thumbnail?,
            thumbnailUrl: String?,
            uploadDate: String?
        ) = thumbnail?.let {
            Trailer(
                type?:"",description ?: "", embedUrl ?: "", name ?: "",
                it, thumbnailUrl ?: "", uploadDate ?: ""
            )
        }

    }
}