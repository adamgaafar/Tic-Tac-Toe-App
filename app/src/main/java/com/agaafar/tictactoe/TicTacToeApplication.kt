package com.agaafar.tictactoe

import android.app.Application
import com.agaafar.tictactoe.data.userDatabase
import com.agaafar.tictactoe.data.userRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TicTacToeApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy {userDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { userRepository(database.userDao()) }
}