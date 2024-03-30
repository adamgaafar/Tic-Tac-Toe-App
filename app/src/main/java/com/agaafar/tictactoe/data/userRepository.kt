package com.agaafar.tictactoe.data

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

class userRepository(private val userDao: userDao) {

    val readAllData: Flow<List<userEntity>> = userDao.readAllData()
   // val getOwnendSkins: Flow<List<Int>> = userDao.getOwnendSkins()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addUser(user: userEntity) {
        userDao.addUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUser(user: userEntity) {
        userDao.updateUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateScore(score:Int,userId:Int){
        userDao.updateScore(score,userId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateSkin(skinId:Int,userId:Int){
        userDao.updateSkin(skinId,userId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateMoney(money:Int,userId:Int){
        userDao.updateMoney(money,userId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateOwnendSkin(skins:List<Int>,userId:Int){
        userDao.updateOwnendSkin(Gson().toJson(skins),userId)
    }


}