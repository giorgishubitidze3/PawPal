package com.spearson.pawpal.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.integrity.internal.i
import com.spearson.pawpal.domain.model.Pal
import com.spearson.pawpal.domain.repository.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val firebaseService: FirebaseService
) : ViewModel(){


    private var _palsList = MutableLiveData<List<Pal>>()
    val palsList : LiveData<List<Pal>> get() = _palsList

    init {
        val currentList = _palsList.value ?: emptyList<Pal>()
        getPal("1")
    }


    fun createPal(pal : Pal){
        viewModelScope.launch{
            firebaseService.createPal(pal)
        }
    }

    fun getPal(id: String){
        viewModelScope.launch{

            val currentList = _palsList.value ?: emptyList()

            var newPal = Pal()

            val result = firebaseService.getPal(id)
            result.onSuccess {
                if (it != null) {
                    newPal = it
                }
            }

            val updatedList = currentList + newPal

            _palsList.value = updatedList
        }
    }


}