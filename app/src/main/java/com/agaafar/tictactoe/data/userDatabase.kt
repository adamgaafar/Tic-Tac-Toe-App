package com.agaafar.tictactoe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.agaafar.tictactoe.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@Database(entities = [userEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntConverters::class)
abstract class userDatabase: RoomDatabase() {

    abstract fun userDao(): userDao

    private class userDatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }
        suspend fun populateDatabase(userDao: userDao){
            // Add sample words.
            var user = userEntity(0,100, listOf(1,2),1,0,0,0,0)
            userDao.addUser(user)

        }
    }

    //all classes can see anything inside this companion object
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: userDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): userDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userDatabase::class.java,
                    "usersees_database"
                ).addCallback(userDatabaseCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
