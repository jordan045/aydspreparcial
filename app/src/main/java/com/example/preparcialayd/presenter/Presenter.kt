package com.example.preparcialayd.presenter

import ayds.observer.Subject
import com.example.preparcialayd.model.entities.CurrencyEntity

interface Presenter {
    val observer: Subject<CurrencyEntity>
    fun fetchPrice(selectedCurrency: String)
}