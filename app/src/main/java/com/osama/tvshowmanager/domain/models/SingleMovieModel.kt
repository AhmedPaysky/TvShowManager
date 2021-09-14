package com.osama.tvshowmanager.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SingleMovieModel(
    val id: String,
    val title: String,
    val season: Float,
    val releaseDate: String
) : Parcelable