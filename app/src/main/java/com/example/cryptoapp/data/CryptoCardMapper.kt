package com.example.cryptoapp.data

import com.example.cryptoapp.domain.CryptoCard

object CryptoCardMapper {
    fun entityToCryptoCard(entity: CryptoCardEntity): CryptoCard {
        return CryptoCard(
            entity.name,
            entity.priceUSD,
            entity.minToday,
            entity.maxToday,
            entity.market,
            entity.lastUpdated,
            entity.imageURL
        )
    }

    fun cryptoCardToEntity(card: CryptoCard): CryptoCardEntity {
        return CryptoCardEntity(
            card.name,
            card.priceUSD,
            card.minToday,
            card.maxToday,
            card.market,
            card.lastUpdated,
            card.imageUrl
        )
    }

    fun entitiesToShopItem(list: List<CryptoCardEntity>): List<CryptoCard> {
        return list.map { entityToCryptoCard(it) }
    }
}