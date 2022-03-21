package ru.cft.team.schools.CurrencyExchange.model

interface Repository {

    fun getDataFromServer(): CurrencyDTO


    fun currencyLoaded(currencyDTO: CurrencyDTO?)

    fun addLoadedListener(listener: OnLoadListener)
    fun removeLoadedListener(listener: OnLoadListener)

    fun interface OnLoadListener {
        fun onLoaded()
    }
}