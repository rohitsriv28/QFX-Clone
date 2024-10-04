package com.example.qfxclone.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.qfxclone.data.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    fun addUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Query("select * from users")
    fun getAllUsers(): List<UserEntity>

    @Query("select * from users where email = :email")
    fun getUserByEmail(email: String): UserEntity

    @Query("select * from users where email = :email and password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): UserEntity
}