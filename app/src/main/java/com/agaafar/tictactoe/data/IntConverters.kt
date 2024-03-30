package com.agaafar.tictactoe.data

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class IntConverters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        val type = object: TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<Int>): String? {
        val type = object: TypeToken<List<Int>>() {}.type
        return Gson().toJson(list, type)
    }

}