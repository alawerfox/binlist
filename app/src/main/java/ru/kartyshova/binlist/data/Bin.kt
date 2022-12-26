package ru.kartyshova.binlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bin (
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean = false,
    val country: Country?,
    val bank: Bank?,
    val binInput: Int = 0
): Parcelable

@Parcelize
data class Number (
    val length: Int = 0,
    val luhn: Boolean = false
): Parcelable

@Parcelize
data class Country (
    val numeric: Int = 0,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int = 0,
    val longitude: Int = 0,
): Parcelable

@Parcelize
data class Bank (
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
): Parcelable
