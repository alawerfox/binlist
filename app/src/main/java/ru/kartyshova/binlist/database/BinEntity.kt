package ru.kartyshova.binlist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_Info")
data class BinEntity(
    @PrimaryKey(autoGenerate = true)
    val binInput: Int,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val length: Int,
    val luhn: Boolean,
    val numeric: Int,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int,
    val bank_name: String,
    val url: String,
    val phone: String,
    val city: String,
)
