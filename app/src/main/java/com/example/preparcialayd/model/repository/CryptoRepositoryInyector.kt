package com.example.preparcialayd.model.repository

import android.content.Context
import com.example.preparcialayd.view.MainScreen

object CryptoRepositoryInyector {
    private lateinit var repository : CryptoRepository

    fun init(mainScreen: MainScreen) {
        val sharedPreferences = (mainScreen as Context).getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        repository = CryptoRepositoryImpl(sharedPreferences)
    }

    fun getRepository(): CryptoRepository {
        return repository
    }
}