package com.spearson.pawpal.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.spearson.pawpal.domain.model.Pal
import com.spearson.pawpal.domain.model.User
import com.spearson.pawpal.domain.repository.FirebaseService
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class FirebaseServiceImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth
): FirebaseService {
    private val usersRef = database.getReference("users")
    private val palsRef = database.getReference("pals")

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun createUser(user: User): Result<Unit> {
        return suspendCancellableCoroutine { continuation ->
            val currentUserId = auth.currentUser?.uid ?: return@suspendCancellableCoroutine
            usersRef.child(currentUserId).setValue(user)
                .addOnSuccessListener {
                    continuation.resume(Result.success(Unit))
                }
                .addOnFailureListener{ exception ->
                     continuation.resume(Result.failure(exception))
                }
        }
    }

    override suspend fun getUser(userId: String): Result<User?> {
        return suspendCancellableCoroutine { continuation ->
            usersRef.child(userId).get()
                .addOnSuccessListener { dataSnapshot ->
                    val user = dataSnapshot.getValue(User::class.java)
                    continuation.resume(Result.success(user))
                }
                .addOnFailureListener{ exception ->
                    continuation.resume(Result.failure(exception))
                }
        }
    }

    override suspend fun createPal(pal: Pal): Result<Unit> {
        return suspendCancellableCoroutine { continuation ->
            val currentPalId = pal.palId ?: return@suspendCancellableCoroutine
            palsRef.child(currentPalId).setValue(pal)
                .addOnSuccessListener {
                    continuation.resume(Result.success(Unit))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }

        }
    }

    override suspend fun getPal(id: String): Result<Pal?> {
        return suspendCancellableCoroutine { continuation ->
            palsRef.child(id).get()
                .addOnSuccessListener { dataSnapshot ->
                    val pal = dataSnapshot.getValue(Pal::class.java)
                    continuation.resume(Result.success(pal))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
        }
    }


}


