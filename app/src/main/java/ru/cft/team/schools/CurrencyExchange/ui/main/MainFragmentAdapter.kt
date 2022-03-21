package ru.cft.team.schools.CurrencyExchange.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cft.team.schools.CurrencyExchange.R
import ru.cft.team.schools.CurrencyExchange.model.CurrencyLocal

class MainFragmentAdapter: RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var currencys: List<CurrencyLocal> = listOf()
    var listener: OnItemClick? = null

    fun setCurrencys(data: List<CurrencyLocal>) {
        currencys = data
        notifyDataSetChanged()
    }

    inner class MainFragmentViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(currency: CurrencyLocal) {
            itemView.apply {
                currency.CharCode.also { findViewById<TextView>(R.id.tvCurrencyCode).text = it }
                currency.Name.also { findViewById<TextView>(R.id.tvCurrencyName).text = it }
                currency.Value.also { findViewById<TextView>(R.id.tvCurrencyValue).text =
                    it.toString()
                }
                setOnClickListener {
                    listener?.onClick(currency)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentViewHolder {
        return MainFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.bind(currencys[position])
    }

    override fun getItemCount(): Int = currencys.size

    fun interface OnItemClick {
        fun onClick(currency: CurrencyLocal)
    }
}