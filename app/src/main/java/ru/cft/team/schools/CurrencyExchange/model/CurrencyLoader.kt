package ru.cft.team.schools.CurrencyExchange.model

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

object CurrencyLoader {

    private const val DEBUG_LOG = "DEBUG_LOG"
    private const val mainURLString = "https://www.cbr-xml-daily.ru/daily_json.js"
    private const val CONNECT_TIMEOUT = 3000
    private const val READ_TIMEOUT = 3000

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        .callTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun loadOkHttpData(listener: OnDataLoadListener) {

        val request: Request = Request.Builder()
            .get()
            .url("$mainURLString")
            .build()

        okHttpClient.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    listener.onFailed(e)
                    Log.d(DEBUG_LOG, "FAIL Connection", e)
                }

                override fun onResponse(call: Call, response: Response) {

                    if (response.isSuccessful) {
                        val currencyDTO = Gson().fromJson(response.body()?.string(), CurrencyDTO::class.java)
                        listener.onLoaded(currencyDTO)
                    } else {
                        listener.onFailed(Exception(response.body()?.string()))
                        Log.d(DEBUG_LOG, "Fail connection $response")
                    }
                }
            })
    }

    interface OnDataLoadListener {
        fun onLoaded(currencyDTO: CurrencyDTO)
        //fun onLoaded(string: String?)
        fun onFailed(throwable: Throwable)
    }


}