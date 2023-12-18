package com.example.cryptoapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository
import com.example.cryptoapp.domain.CurrencyNames

class DataBaseRepository(context: Context): CryptoCardsRepository {

    private val dao = CryptoCardDB.getInstance(context).getDao()

    override suspend fun addCryptoCard(cryptoCard: CryptoCard) {
        dao.addCryptoCard(CryptoCardMapper.cryptoCardToEntity(cryptoCard))
    }

    override fun getCryptoCard(name: String): LiveData<CryptoCard> {
        return MediatorLiveData<CryptoCard>().apply {
            addSource(dao.getCryptoCard(name)) {
                value = CryptoCardMapper.entityToCryptoCard(it)
            }
        }
    }

    override fun getCryptoCardsList(): LiveData<List<CryptoCard>> {
        return MediatorLiveData<List<CryptoCard>>().apply {
            addSource(dao.getCryptoCardsList()) {
                value = CryptoCardMapper.entitiesToShopItem(it)
            }
        }
    }

    override suspend fun editCryptoCard(cryptoCard: CryptoCard) {
        dao.editCryptoCard(CryptoCardMapper.cryptoCardToEntity(cryptoCard))
    }

    override suspend fun removeCryptoCard(cryptoCard: CryptoCard) {
        dao.removeCryptoCard(CryptoCardMapper.cryptoCardToEntity(cryptoCard))
    }

    override suspend fun getCurrencyNamesList(): CurrencyNames {
        val names = CurrencyNames()
        names.namesList.apply {
            addAll(dao.getCurrencyNamesList())
        }
        return names
    }

}