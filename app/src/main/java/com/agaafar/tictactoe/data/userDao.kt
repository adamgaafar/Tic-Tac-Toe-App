package com.agaafar.tictactoe.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface userDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user:userEntity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():Flow<List<userEntity>>

  /*  @Query("SELECT skins FROM user_table WHERE id = 1 ORDER BY id ASC ")
    fun getOwnendSkins():Flow<List<Int>>
*/
    @Update
    fun updateUser(user: userEntity)

    @Query("UPDATE user_table SET score = score + :score WHERE id = :userId")
    fun updateScore(score:Int,userId:Int)

    @Query("UPDATE user_table SET score = :money WHERE id = :userId")
    fun updateMoney(money:Int,userId:Int)

    @Query("UPDATE user_table SET currentSkin = :skinId WHERE id = :userId")
    fun updateSkin(skinId:Int,userId:Int)


    //add updatePairSkins
    @Query("UPDATE user_table SET skins = :skinsList WHERE id = :userId")
    fun updateOwnendSkin(skinsList:String,userId:Int)




    @Delete
    fun deleteUser(user: userEntity)


}