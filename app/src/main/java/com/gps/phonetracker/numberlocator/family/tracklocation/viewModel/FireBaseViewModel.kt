package com.gps.phonetracker.numberlocator.family.tracklocation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gps.phonetracker.numberlocator.family.tracklocation.model.Users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FireBaseViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    private val usersCollection = firestore.collection("users")



    private val _onChange = MutableStateFlow<List<Users>>(emptyList())
    val onChange: StateFlow<List<Users>> get() = _onChange.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> get() = _isSuccess

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    fun addUserFireBase(users: Users) {
        usersCollection.add(users).addOnCompleteListener {
            if (it.isSuccessful) {
                _isSuccess.value = true
            } else {
                _isError.value = true
            }
        }
    }

    fun onValueChangeListens() {
        usersCollection.addSnapshotListener { snapshot, exception ->
            val list = ArrayList<Users>()
            if (exception != null) {
                 _isError.value = true
                return@addSnapshotListener
            }

            snapshot?.let {
                Log.d(".//////////","ok")
                val items = it.toObjects(Users::class.java)
                _onChange.value = items
            }
         }
    }
}