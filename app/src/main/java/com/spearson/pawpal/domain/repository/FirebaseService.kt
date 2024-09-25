package com.spearson.pawpal.domain.repository

import com.spearson.pawpal.domain.model.User

interface FirebaseService {

    suspend fun createUser(user: User): Result<Unit>

    suspend fun getUser(id: String): Result<User?>
}