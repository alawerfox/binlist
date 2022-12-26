package ru.kartyshova.binlist.data

import ru.kartyshova.binlist.database.BinDao
import ru.kartyshova.binlist.database.BinEntity

interface BinRepository {
    suspend fun getBin(search: Int): Bin
}

class BinRepositoryImpl(
    private val binApi: BinApi
): BinRepository {
    override suspend fun getBin(search: Int): Bin {
        return binApi.getBinList(search)
    }
}

interface LocalBinRepository {
    suspend fun insert(bin: Bin)
    suspend fun getAll(): List<Bin>
}

class LocalBinRepositoryImpl(
    private val binDao: BinDao
) : LocalBinRepository {
    override suspend fun insert(bin: Bin) {
        val binEntity = BinEntity(
            length = bin.number?.length ?: 0,
            luhn = bin.number?.luhn ?: false,
            scheme = bin.scheme ?: "",
            type = bin.type ?: "",
            brand = bin.brand ?: "",
            prepaid = bin.prepaid,
            numeric = bin.country?.numeric ?: 0,
            alpha2 = bin.country?.alpha2 ?: "",
            name = bin.country?.name ?: "",
            emoji = bin.country?.emoji ?: "",
            currency = bin.country?.currency ?: "",
            latitude = bin.country?.latitude ?: 0,
            longitude = bin.country?.longitude ?: 0,
            bank_name = bin.bank?.name ?: "",
            url = bin.bank?.url ?: "",
            phone = bin.bank?.phone ?: "",
            city = bin.bank?.city ?: "",
            binInput = bin.binInput
        )

        binDao.insert(binEntity)
    }

    override suspend fun getAll(): List<Bin> {
        return binDao.getBinInfo()
            .map {
                Bin(
                    Number(it.length, it.luhn),
                    it.scheme,
                    it.type,
                    it.brand,
                    it.prepaid,
                    Country(it.numeric, it.alpha2, it.name,it.emoji, it.currency, it.latitude,it.longitude),
                    Bank(it.bank_name,it.url,it.phone,it.city),
                    it.binInput
                )
            }
    }

}

