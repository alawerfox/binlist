package ru.kartyshova.binlist.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kartyshova.binlist.data.Bin
import ru.kartyshova.binlist.databinding.ItemHistoryBinding

class BinHistoryAdapter : RecyclerView.Adapter<BinHistoryAdapter.BinHistoryViewHolder>() {

    private var bin: List<Bin> = emptyList()
    private var onBinClick: (Bin) -> Unit = {}

    fun setBinClick(listener: (Bin) -> Unit) {
        onBinClick = listener
    }

    fun setBins(items: List<Bin>) {
        bin = items
        notifyItemRangeChanged(0, bin.size)
    }

    class BinHistoryViewHolder(viewBinding: ItemHistoryBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        val history = viewBinding.history
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinHistoryViewHolder {
        val viewBinding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BinHistoryViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BinHistoryViewHolder, position: Int) {
        val binHistory = bin[position]
        holder.history.text = "${binHistory.binInput}"

        holder.itemView.setOnClickListener {
            onBinClick(binHistory)
        }
    }

    override fun getItemCount(): Int = bin.size

}