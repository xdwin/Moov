package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetail(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val production_countries: List<ProductionCountry>,
    @SerializedName("release_date")
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
) : Serializable