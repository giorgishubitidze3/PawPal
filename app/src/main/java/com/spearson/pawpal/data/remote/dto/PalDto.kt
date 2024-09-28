package com.spearson.pawpal.data.remote.dto

import android.media.Image
import com.spearson.pawpal.domain.model.User

data class PalDto (
    val palId : String  = "",
    val name : String = "",
    val gender : String = "",
    val age : String = "",
    val weight : String = "",
    val location : String ="",
    val owner : String = "",
    val photos : String = ""
)