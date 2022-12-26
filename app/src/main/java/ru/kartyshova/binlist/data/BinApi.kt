package ru.kartyshova.binlist.data

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinApi {
    @GET("/{id}")
    @Headers("Accept-Version: 3")
    suspend fun getBinList (@Path("id") query: Int): Bin
}