package com.sb.myrecords.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.myrecords.data.entities.User
import com.sb.myrecords.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private var userId: Int = 1
    lateinit var updatedUser: User

    fun update(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    val user by lazy { repository.getUser(userId) }

}
