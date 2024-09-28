package com.spearson.pawpal.domain.repository

import com.spearson.pawpal.domain.model.Pal
import com.spearson.pawpal.domain.model.User

interface FirebaseService {

    suspend fun createUser(user: User): Result<Unit>

    suspend fun getUser(id: String): Result<User?>

    suspend fun createPal(pal: Pal): Result<Unit>

    suspend fun getPal(id: String): Result<Pal?>
}