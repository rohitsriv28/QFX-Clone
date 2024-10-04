package com.example.qfxclone.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.qfxclone.data.converters.DatabaseConverter
import java.util.Date


@TypeConverters(DatabaseConverter::class)
@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class UserEntity(
    @PrimaryKey(true)
    val userId: Long = 0,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("middle_name")
    val middleName: String,
    @ColumnInfo("last_name")
    val lastName: String,
    val date: Date? = null,
    val email: String,
    val password: String
)