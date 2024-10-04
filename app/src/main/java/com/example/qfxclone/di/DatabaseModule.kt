package com.example.qfxclone.di

import android.content.Context
import androidx.room.Room
import com.example.qfxclone.data.dao.UserDao
import com.example.qfxclone.data.database.QfxDatabase
import com.example.qfxclone.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesQfxDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QfxDatabase::class.java, "QfxDb")
            .fallbackToDestructiveMigrationFrom().build()

    @Singleton
    @Provides
    fun providesUserDao(qfxDatabase: QfxDatabase) = qfxDatabase.userDao

    @Singleton
    @Provides
    fun providesUserRepository(userDao: UserDao) = UserRepository(userDao)
}