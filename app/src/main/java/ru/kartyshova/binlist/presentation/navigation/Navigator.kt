package ru.kartyshova.binlist.presentation.navigation

import androidx.navigation.NavController
import ru.kartyshova.binlist.data.Bin
import ru.kartyshova.binlist.presentation.search.SearchFragmentDirections

class Navigator {

    private var navController: NavController? = null

    fun bind(value: NavController) {
        navController = value
    }

    fun unbind() {
        navController = null
    }

    fun openBinInfoFragment(binBin: Bin) {
        navController?.navigate(
            SearchFragmentDirections.actionSearchFragmentToBinInfoFragment(binBin)
        )
    }

}