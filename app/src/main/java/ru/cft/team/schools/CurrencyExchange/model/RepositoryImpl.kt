package ru.cft.team.schools.CurrencyExchange.model

class RepositoryImpl: Repository {

    private val listeners: MutableList<Repository.OnLoadListener> = mutableListOf()
    private var currencyDTO: CurrencyDTO? = null


    override fun getDataFromServer(): CurrencyDTO {
        //TODO("Not yet implemented")
        return currencyDTO as CurrencyDTO
    }

    override fun currencyLoaded(currencyDTO: CurrencyDTO?) {
        //TODO("Not yet implemented")
    }

    // Добавляем слушатель
    override fun addLoadedListener(listener: Repository.OnLoadListener) {
        listeners.add(listener)
    }

    // Удаляем слушатель
    override fun removeLoadedListener(listener: Repository.OnLoadListener) {
        listeners.remove(listener)
    }
}