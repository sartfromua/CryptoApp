package com.example.cryptoapp.domain.retrofit

import android.os.SystemClock
import com.example.cryptoapp.UNDEFINED
import com.example.cryptoapp.domain.CryptoCard

object ResponseCryptoCardMapper {

    var topPlace = 1
    fun responseToCards(response: CryptoDataResponse): List<CryptoCard> {
        topPlace = 1
        val cards: MutableList<CryptoCard> = emptyList<CryptoCard>().toMutableList()
        for (i in 0..<response.Data.size) {
            response.Data[i].RAW?.let {
                cards.add(
                    CryptoCard(
                        name = it.USD.FROMSYMBOL ?: "",
                        priceUSD = it.USD.PRICE ?: UNDEFINED,
                        minToday = it.USD.LOW24HOUR ?: UNDEFINED,
                        maxToday = it.USD.HIGH24HOUR ?: UNDEFINED,
                        market = it.USD.MARKET ?: "",
                        lastUpdated = SystemClock.elapsedRealtime(),
                        imageUrl = it.USD.IMAGEURL ?: "",
                        changeDay = it.USD.CHANGEDAY ?: UNDEFINED,
                        changeHour = it.USD.CHANGEHOUR ?: UNDEFINED,
                        topPlace = topPlace++
                    )
                )
            }
        }
        return cards
    }
}