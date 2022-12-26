package ru.kartyshova.binlist.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kartyshova.binlist.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var searchBinding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModel()

    private val binHistoryAdapter = BinHistoryAdapter().apply {
        setBinClick {
            viewModel.openBin(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.historyItems.observe(this) {
            binHistoryAdapter.setBins(it)
        }

        viewModel.error.observe(this) {
            searchBinding?.error?.isVisible = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding?.historyItem?.adapter = binHistoryAdapter
        searchBinding?.searchFiled?.doOnTextChanged { text, _, _, _ ->
            val string = text.toString()
            if (string.isBlank()) return@doOnTextChanged
            viewModel.search(string.toInt())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadHistory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchBinding = null
    }
}