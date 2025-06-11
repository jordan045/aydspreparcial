package com.example.preparcialayd.presenter

import android.util.Log
import ayds.observer.Subject
import com.example.preparcialayd.model.repository.CryptoRepository
import com.example.preparcialayd.model.entities.CurrencyEntity
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class PresenterImpl(
    private val repository: CryptoRepository) : Presenter {
    override val observer = Subject<CurrencyEntity>()

    override fun fetchPrice(selectedCurrency: String) {
        thread {
            val result = repository.fetchPrice(selectedCurrency)
            observer.notify(CurrencyEntity(selectedCurrency, result.roundToInt()))
        }
    }
}