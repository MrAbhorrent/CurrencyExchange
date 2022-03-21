package ru.cft.team.schools.CurrencyExchange.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import ru.cft.team.schools.CurrencyExchange.R
import ru.cft.team.schools.CurrencyExchange.model.CurrencyLocal

class ConvertCurrencyFragment : Fragment() {

    private val charCodeRUB = "RUB"
    private var exchangeCoefficient: Float = 0f
    private var exchangeCharCode: String = ""
    private lateinit var edTextView: EditText
    private lateinit var tvTextCalculateExchange: TextView
    private lateinit var btnCalculate: Button

    companion object {
        fun newInstance(bundle: Bundle?): ConvertCurrencyFragment {
            val fragment = ConvertCurrencyFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convert_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edTextView = view.findViewById(R.id.edtValueCash)
        btnCalculate = view.findViewById(R.id.btnCalculateExchange)
        tvTextCalculateExchange = view.findViewById(R.id.tvTextCalculateExchange)

        arguments
            ?.getParcelable<CurrencyLocal>(getString(R.string.KEY_EXTRA))
            ?.let { currencyLocal ->
                exchangeCoefficient = currencyLocal.Value ?: 0f
                exchangeCharCode = currencyLocal.CharCode ?: ""
            }

        btnCalculate.setOnClickListener {

            var valueRUB = edTextView.text.toString().toFloat()
            if (valueRUB > 0 && exchangeCoefficient != 0f) {
                tvTextCalculateExchange.text = "Можно купить:\n ${valueRUB/exchangeCoefficient}$exchangeCharCode "
            }
        }
    }

}