package ru.cft.team.schools.CurrencyExchange.viewmodel

import android.os.SystemClock.sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cft.team.schools.CurrencyExchange.model.CurrencyDTO
import ru.cft.team.schools.CurrencyExchange.model.CurrencyLoader
import ru.cft.team.schools.CurrencyExchange.model.CurrencyLocal
import java.util.*

class MainViewModel(): ViewModel() {

    internal val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        getDataFromServer()
        return liveDataToObserve
    }

    private fun getDataFromServer() {

        liveDataToObserve.value = AppState.Loading
        CurrencyLoader.loadOkHttpData(object : CurrencyLoader.OnDataLoadListener {
            override fun onLoaded(currencyDTO: CurrencyDTO) {
                val currency = mutableListOf<CurrencyLocal>()
                currencyDTO.Valute.forEach{ it ->
                    val currencyOne = CurrencyLocal(
                        it.value.ID,
                        it.value.NumCode,
                        it.value.CharCode,
                        it.value.Nominal,
                        it.value.Name,
                        it.value.Value,
                        it.value.Previous
                    )
                    currency.add(currencyOne)
                }
                liveDataToObserve.postValue(AppState.Success(currency))
            }

            override fun onFailed(throwable: Throwable) {
                liveDataToObserve.postValue(AppState.Error(throwable))
            }
        })
    }


}