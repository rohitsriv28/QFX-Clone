package com.example.qfxclone.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.qfxclone.data.dao.UserDao
import com.example.qfxclone.data.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class QfxDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}