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

    fun responseToOneCard(response: CryptoDataInfoOneCrypto, name: String, topPlace: Int): CryptoCard {
        var card = CryptoCard()
        response.RAW[name]?.get("USD")?.let {
            card =  CryptoCard(
                name = name,
                priceUSD = it.PRICE ?: UNDEFINED,
                minToday = it.LOW24HOUR ?: UNDEFINED,
                maxToday = it.HIGH24HOUR ?: UNDEFINED,
                market = it.MARKET ?: "",
                lastUpdated = SystemClock.elapsedRealtime(),
                imageUrl = it.IMAGEURL ?: "",
                changeDay = it.CHANGEDAY ?: UNDEFINED,
                changeHour = it.CHANGEHOUR ?: UNDEFINED,
                topPlace = topPlace
            )
        }
        return card
    }
}