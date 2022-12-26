package ru.kartyshova.binlist.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.binlist.data.LocalBinRepository
import ru.kartyshova.binlist.data.Bin

class GetHistoryUseCase(private val localBinRepository: LocalBinRepository) {
    suspend operator fun invoke(): List<Bin> = withContext(Dispatchers.IO) {
        localBinRepository.getAll()
    }
}