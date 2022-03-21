package ru.cft.team.schools.CurrencyExchange.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.cft.team.schools.CurrencyExchange.R
import ru.cft.team.schools.CurrencyExchange.databinding.MainFragmentBinding
import ru.cft.team.schools.CurrencyExchange.model.CurrencyLocal
import ru.cft.team.schools.CurrencyExchange.viewmodel.AppState
import ru.cft.team.schools.CurrencyExchange.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MainFragmentAdapter()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMainScreen.adapter = adapter
        binding.rvMainScreen.layoutManager = LinearLayoutManager(requireActivity())

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveDataToObserve.observe(
            viewLifecycleOwner
        ) { data ->
            renderData(data)
        }
        // Запрашиваем данные у ViewModel
        viewModel.getData()
        // Слушатель на FAB для обновления данных
        binding.fabUpdate.setOnClickListener {
            viewModel.getData()
        }
    }

    private fun renderData(state: AppState) {
        Toast.makeText(context, "Будут данные", Toast.LENGTH_SHORT).show()
        // TODO: Нарисуем отображение списка
        when (state) {
            is AppState.Success<*> -> {
                binding.onLoadingContainer.hide()
                val currencys = state.data as List<CurrencyLocal>
                adapter.setCurrencys(currencys)
                adapter.listener = MainFragmentAdapter.OnItemClick { currency ->
                    Toast.makeText(requireActivity(), "Выбрано: ${currency.Name}", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle().apply {
                        putParcelable(getString(R.string.KEY_EXTRA), currency)
                    }
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main, ConvertCurrencyFragment.newInstance(bundle))
                        .addToBackStack("main")
                        .commit()
                }
            }
            is AppState.Error -> {
                binding.onLoadingContainer.show()
                Snackbar.make(binding.root, "Ошибка загрузки:\n ${state.error.message.toString()}", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.messageRepeatLoading)) {
                        viewModel.getData()
                    }
                    .show()
            }
            is AppState.Loading ->
                binding.onLoadingContainer.show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}