package com.example.cryptoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository
import java.lang.RuntimeException
import java.util.Date

object CryptoCardRepositoryImpl: CryptoCardsRepository {

    private val cryptoCards = sortedSetOf<CryptoCard>(compareBy{it.name})
    val liveData = MutableLiveData<List<CryptoCard>>()

    init {
        addCryptoCard(CryptoCard("BTC", 43000.0, 1230000.0, 42999.9, 44000.0, 3.5, "30 sec ago"))
        addCryptoCard(CryptoCard("ETH", 1900.0, 65000.0, 6400.9, 6500.0, 1.5, "10 min ago"))
        addCryptoCard(CryptoCard("USDT", 1.03, 37.0, 36.9, 38.0, 0.5, "1 hour ago"))
    }

    override fun addCryptoCard(cryptoCard: CryptoCard) {
        cryptoCards.add(cryptoCard)

        update()
    }

    override fun getCryptoCard(name: String): CryptoCard {
        return cryptoCards.find {it.name == name}
            ?: throw RuntimeException("Card not found!")
    }

    override fun getCryptoCardsList(): LiveData<List<CryptoCard>> {
        return liveData
    }

    override fun editCryptoCard(cryptoCard: CryptoCard) {
        removeCryptoCard(cryptoCard)
        addCryptoCard(cryptoCard)
    }

    override fun removeCryptoCard(cryptoCard: CryptoCard) {
        cryptoCards.remove(cryptoCard)

        update()
    }

    private fun update() {
        liveData.value = cryptoCards.toList()
    }

}
