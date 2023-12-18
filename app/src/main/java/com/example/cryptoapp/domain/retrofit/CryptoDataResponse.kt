package com.example.cryptoapp.domain.retrofit

data class CryptoDataResponse(
    val Data: List<CryptoDataInfo>
)

data class CryptoDataInfo (
    val RAW: RawInfo?
)

data class CryptoDataInfoOneCrypto (
    val RAW: Map<String, Map<String, CryptoInfo>>
)

data class RawInfo(
    val USD: CryptoInfo
)