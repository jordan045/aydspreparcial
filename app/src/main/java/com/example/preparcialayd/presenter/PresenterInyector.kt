package com.example.preparcialayd.presenter

import com.example.preparcialayd.model.repository.CryptoRepository
import com.example.preparcialayd.model.repository.CryptoRepositoryInyector
import com.example.preparcialayd.view.MainScreen

object PresenterInyector {
    private lateinit var repository : CryptoRepository
    private lateinit var presenter : Presenter

    fun init(mainScreen: MainScreen) {
        CryptoRepositoryInyector.init(mainScreen)

        repository = CryptoRepositoryInyector.getRepository()
        presenter = PresenterImpl(repository)
    }

    fun getPresenter(): Presenter {
        return presenter
    }

}