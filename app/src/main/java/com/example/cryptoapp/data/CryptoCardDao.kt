package com.example.cryptoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CryptoCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCryptoCard(card: CryptoCardEntity)

    @Update
    suspend fun editCryptoCard(card: CryptoCardEntity)

    @Query("SELECT * FROM cards_table WHERE name_card ==:cardName LIMIT 1")
    suspend fun getCryptoCard(cardName: String): CryptoCardEntity

    @Query("SELECT * FROM cards_table ORDER BY name_card ASC")
    fun getCryptoCardsList(): LiveData<List<CryptoCardEntity>>

    @Delete
    suspend fun removeCryptoCard(card: CryptoCardEntity)
}