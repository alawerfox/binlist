package ru.kartyshova.binlist.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.binlist.data.LocalBinRepository
import ru.kartyshova.binlist.data.Bin

class SaveBinUseCase(private val localBinRepository: LocalBinRepository) {
    suspend operator fun invoke(bin: Bin) = withContext(Dispatchers.IO) {
        localBinRepository.insert(bin)
    }
}