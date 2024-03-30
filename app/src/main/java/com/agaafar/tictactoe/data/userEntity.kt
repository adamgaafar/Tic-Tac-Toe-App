package com.agaafar.tictactoe.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
data class userEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var score:Int = 0,
    var skins:List<Int> = listOf<Int>(),
    var currentSkin:Int = 0,
    //add all skins to store all purchased skins by simple number like 1 and 2 ,3,4,5,6
    var firstAd:Int = 0,
    var secondAd:Int = 0,
    var thirdAd:Int = 0,
    var forthAd:Int = 0,
)
