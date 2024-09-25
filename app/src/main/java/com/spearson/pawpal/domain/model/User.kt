package com.spearson.pawpal.domain.model

import com.google.android.gms.common.util.Strings

data class User(
    val userId: String = "",
    val username: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val profilePic: String = "default"
)
