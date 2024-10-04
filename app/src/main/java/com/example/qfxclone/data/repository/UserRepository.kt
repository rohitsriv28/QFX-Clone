package com.example.qfxclone.data.repository

import com.example.qfxclone.data.dao.UserDao
import com.example.qfxclone.data.entities.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(val userDao: UserDao) {
    fun addUser(userEntity: UserEntity) {
        userDao.addUser(userEntity)
    }

    fun getUserByEmail(email: String): UserEntity {
        return userDao.getUserByEmail(email)
    }

    fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    fun getUserByEmailAndPassword(email: String, password: String): UserEntity {
        return userDao.getUserByEmailAndPassword(email, password)
    }
}