package ru.kartyshova.binlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: BinEntity)

    @Query("SELECT * FROM bin_Info")
    fun getBinInfo(): List<BinEntity>
}