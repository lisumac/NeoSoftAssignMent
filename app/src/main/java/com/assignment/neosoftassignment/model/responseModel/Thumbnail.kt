package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    @SerializedName("@type")
    val type: String,
    //val @type: String,
    val contentUrl: String
):Parcelable {

}