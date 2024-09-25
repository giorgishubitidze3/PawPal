package com.spearson.pawpal.presentation.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spearson.pawpal.domain.model.User
import com.spearson.pawpal.domain.repository.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val firebaseService: FirebaseService
): ViewModel(){
    val user = MutableLiveData<User?>()
    val errorMessage = MutableLiveData<String?>()

    fun fetchUser(userId:String){
        viewModelScope.launch {
            val result = firebaseService.getUser(userId)
            result.onSuccess {
                user.value = it
                Log.d("Firebase", "User data retrieved successfully")
            }
            result.onFailure{
                errorMessage.value = it.message
                Log.e("Firebase", "Failed to retrieve user data", it)
            }
        }
    }
}