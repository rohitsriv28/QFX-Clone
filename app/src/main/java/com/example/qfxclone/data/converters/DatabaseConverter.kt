package com.example.qfxclone.data.converters

import androidx.room.TypeConverter
import java.util.Date

object DatabaseConverter {
    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        val dt = date
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long?): Date? {
        if (value == null)
            return null
        return Date(value)
    }
}