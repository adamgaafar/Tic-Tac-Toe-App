package com.agaafar.tictactoe.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class userViewModel(private val repository: userRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val readAllData: LiveData<List<userEntity>> = repository.readAllData.asLiveData()
   // val getOwnenedSkins:LiveData<List<Int>> = repository.getOwnendSkins.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addUser(user: userEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUser(user)
    }

    fun updateScore(score:Int,userId:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.updateScore(score, userId)
    }

     fun updateSkin(skinId:Int,userId:Int) = viewModelScope.launch(Dispatchers.IO){
         repository.updateSkin(skinId, userId)
     }

    fun updateMoney(money:Int,userId:Int) = viewModelScope.launch(Dispatchers.IO){
         repository.updateMoney(money, userId)
     }

     fun updateOwnendSkin(skins:List<Int>,userId:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.updateOwnendSkin(skins,userId)
    }





}

class userViewModelFactory(private val repository: userRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(userViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return userViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}