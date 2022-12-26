package ru.kartyshova.binlist.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kartyshova.binlist.presentation.navigation.Navigator
import ru.kartyshova.binlist.data.Bin
import ru.kartyshova.binlist.domain.usecase.GetBinUseCase
import ru.kartyshova.binlist.domain.usecase.GetHistoryUseCase
import ru.kartyshova.binlist.domain.usecase.SaveBinUseCase

class SearchViewModel(
    private var navigator: Navigator,
    private val getBinUseCase: GetBinUseCase,
    private val saveBinUseCase: SaveBinUseCase,
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    val historyItems = MutableLiveData<List<Bin>>()
    val error = MutableLiveData<Boolean>()

    fun loadHistory() {
        viewModelScope.launch {
            val items = getHistoryUseCase()
            historyItems.value = items
        }
    }

    fun search(int: Int) {
        if (int.toString().length < 8) return

        viewModelScope.launch {
            getBinUseCase(int)
                .onSuccess {
                    val entity = it.copy(binInput = int)
                    saveBinUseCase(entity)
                    openBin(it)
                    error.value = false
                }
                .onFailure {
                    Log.d("Error", it.message, it)
                    error.value = true
                }
        }
    }

    fun openBin(bin: Bin) {
        navigator.openBinInfoFragment(bin)
    }
}