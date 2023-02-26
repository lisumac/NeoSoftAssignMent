package com.assignment.neosoftassignment.model.responseModel

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import com.assignment.neosoftassignment.model.roomdataBase.*
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "MovieList")
data class MovieResponseItem(

    @PrimaryKey(autoGenerate = true)
    var movieResponsePrimaryKey: Int,
    // val parentResponsePrimaryKey:String,
    /*val @context: String,
    val @type: String,*/
    @TypeConverters(ActorConverters::class)
    @NotNull
    var actor: List<Actor>,
    @TypeConverters(AggregateRatingConverters::class)
    @NotNull
    var aggregateRating: AggregateRating,

    var contentRating: String = " ",
    @TypeConverters(CreatorsConverters::class)
    @NonNull
    var creator: List<Creator>,
    var datePublished: String,
    var description: String,
    @TypeConverters(DirectorConverters::class)
    @NotNull
    var director: List<Director>,
    var duration: String,
    @TypeConverters(GenreConverters::class)
    @NotNull
    var genre: List<String>,
    var image: String,
    var keywords: String,
    var name: String,
    @TypeConverters(TrailorConverters::class)
    var trailer: Trailer? = null,
    var url: String
) : Parcelable {
    companion object {

        operator fun invoke(
            movieResponsePrimaryKey: Int,
            actor: List<Actor>? = null,
            aggregateRating: AggregateRating? = null,
            contentRating: String,
            creator: List<Creator>,
            datePublished: String,
            description: String,
            director: List<Director>,
            duration: String,
            genre: List<String>,
            image: String,
            keywords: String,
            name: String,
            trailer: Trailer,
            url: String
        ) =
            MovieResponseItem(
                movieResponsePrimaryKey ?: 0,
                actor ?: ArrayList<Actor>(),
                aggregateRating ?: AggregateRating("", 0, 0.0, ""),
                contentRating ?: "",
                creator ?: ArrayList<Creator>(),
                datePublished ?: "",
                description ?: "",
                director ?: ArrayList<Director>(),
                duration ?: "",
                genre ?: ArrayList<String>(),
                image ?: "",
                keywords ?: "",
                name ?: "",
                trailer ?: Trailer("", "", "", "", Thumbnail("", "")),
                url ?: ""

            )

    }


}
