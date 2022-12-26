package ru.kartyshova.binlist.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.binlist.data.Bin
import ru.kartyshova.binlist.data.BinRepository

class GetBinUseCase(private val binRepository: BinRepository) {
    suspend operator fun invoke(search: Int): Result<Bin> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            binRepository.getBin(search)
        }
    }

}
