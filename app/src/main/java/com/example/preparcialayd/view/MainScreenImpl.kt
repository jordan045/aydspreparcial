package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.R
import com.example.preparcialayd.presenter.Presenter
import com.example.preparcialayd.presenter.PresenterInyector

interface MainScreen {

}

private val currencies = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

class MainScreenImpl : AppCompatActivity(), MainScreen {
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initModule(this)
        initObserver()
        initSpinner()
    }

    private fun initSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinnerMonedas)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCurrency = currencies[position]
                presenter.fetchPrice(selectedCurrency)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun initObserver() {
        presenter.observer.subscribe { result ->
            onPrice(result.symbol, result.price)
        }
    }

    private fun initModule(mainScreen: MainScreen) {
        MainScreenInyector.init(mainScreen)
        presenter = PresenterInyector.getPresenter()
    }

    private fun onPrice(symbol: String, price: Int) {
        val message = "$symbol – $price"
        runOnUiThread {
            findViewById<TextView>(R.id.textPrecio).text = message
        }
    }
}