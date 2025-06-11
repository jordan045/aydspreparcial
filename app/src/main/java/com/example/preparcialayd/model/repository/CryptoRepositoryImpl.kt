package com.example.preparcialayd.model.repository

import com.example.preparcialayd.model.external.CryptoExternalStorage
import android.content.SharedPreferences
import androidx.core.content.edit

class CryptoRepositoryImpl(
    private val sharedPreferences : SharedPreferences
    //private val localData : LocalData nos falto
    //private val externalData : ExternalData nos falto

) : CryptoRepository{
    override fun fetchPrice(symbol: String): Double {
        val savedValue = sharedPreferences.getString(symbol, null)

        return if(savedValue != null) {
            getValueFromLocalStorage(savedValue) //esto est bien modulizar
        } else {
            getValueFromExternalStorage(symbol) //esto est bien es modulizar
        }
    }

    private fun getValueFromExternalStorage(symbol: String): Double {
        val result = CryptoExternalStorage().get(symbol)
        sharedPreferences.edit { putString(symbol, result.toString()) }
        return result
    }

    private fun getValueFromLocalStorage(savedValue: String): Double {
        return try {
            savedValue.toDouble()
        } catch (e: Exception) {
            0.0
        }
    }
}

/*
* la api x no se usa
*
* con la otra apí hago
* interface local data{
*   fun getData(): Double
* }
*
* interface externalData{
*   fun getData(): Double
* }
* */