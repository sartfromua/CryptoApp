package com.example.cryptoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cryptoapp.domain.CurrencyNames

@Dao
interface CryptoCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCryptoCard(card: CryptoCardEntity)

    @Update
    suspend fun editCryptoCard(card: CryptoCardEntity)

    @Query("SELECT * FROM cards_table WHERE name_card ==:cardName LIMIT 1")
    fun getCryptoCard(cardName: String): LiveData<CryptoCardEntity>

    @Query("SELECT * FROM cards_table ORDER BY last_updated ASC")
    fun getCryptoCardsList(): LiveData<List<CryptoCardEntity>>

    @Query("SELECT name_card FROM cards_table")
    suspend fun getCurrencyNamesList(): List<String>

    @Delete
    suspend fun removeCryptoCard(card: CryptoCardEntity)
}