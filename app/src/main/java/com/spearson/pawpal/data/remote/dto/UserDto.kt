package com.spearson.pawpal.data.remote.dto

import com.google.android.gms.common.util.Strings

data class UserDto (
    val userId: String = "",
    val username: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val profilePic: String = "default"
)