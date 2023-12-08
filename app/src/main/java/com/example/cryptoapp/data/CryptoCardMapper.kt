package com.example.cryptoapp.data

import com.example.cryptoapp.domain.CryptoCard

object CryptoCardMapper {
    fun entityToCryptoCard(entity: CryptoCardEntity): CryptoCard {
        return CryptoCard(
            entity.name,
            entity.priceUSD,
            entity.secondCurrencyName,
            entity.secondPrice,
            entity.minToday,
            entity.maxToday,
            entity.lastUpdated
        )
    }

    fun cryptoCardToEntity(card: CryptoCard): CryptoCardEntity {
        return CryptoCardEntity(
            card.name,
            card.priceUSD,
            card.secondCurrencyName,
            card.secondPrice,
            card.minToday,
            card.maxToday,
            card.lastUpdated
        )
    }

    fun entitiesToShopItem(list: List<CryptoCardEntity>): List<CryptoCard> {
        return list.map { entityToCryptoCard(it) }
    }
}