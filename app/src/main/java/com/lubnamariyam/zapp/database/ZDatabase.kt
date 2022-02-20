package com.lubnamariyam.zapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FeedEntity::class], version = 1, exportSchema = false)
abstract class ZDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

    companion object {
        @Volatile
        private var INSTANCE: ZDatabase? = null

        fun getDatabase(context: Context): ZDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ZDatabase::class.java, "ZAppDatabase")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}