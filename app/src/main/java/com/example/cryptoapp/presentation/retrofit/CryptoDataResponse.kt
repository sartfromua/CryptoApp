package com.example.cryptoapp.presentation.retrofit

data class CryptoDataResponse(
    val RAW: Map<String, Map<String, CryptoInfo>>
)