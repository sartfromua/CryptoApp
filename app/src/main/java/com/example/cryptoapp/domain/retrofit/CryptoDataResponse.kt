package com.example.cryptoapp.domain.retrofit

data class CryptoDataResponse(
    val Data: List<CryptoDataInfo>
)

data class CryptoDataInfo (
    val RAW: RawInfo?
)

data class RawInfo(
    val USD: CryptoInfo
)