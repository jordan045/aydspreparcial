package com.example.preparcialayd.model.repository

interface CryptoRepository {
    fun fetchPrice(symbol: String): Double
}