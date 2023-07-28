package com.ghostdev.contactsmanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    //Singleton design pattern
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    //Create the database object if it does not exist
                    instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "users_db" ).build()
                }
                return instance
            }
        }
    }
}