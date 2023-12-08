package com.example.cryptoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoCardEntity::class], version = 1)
abstract class CryptoCardDB: RoomDatabase() {
    abstract fun getDao(): CryptoCardDao

    companion object{
        // Singleton prevents multiple instances of database opening at the
        // same time.
        private var INSTANCE: CryptoCardDB? = null

        fun getInstance(context: Context): CryptoCardDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoCardDB::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}