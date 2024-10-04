package com.example.qfxclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.qfxclone.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
}