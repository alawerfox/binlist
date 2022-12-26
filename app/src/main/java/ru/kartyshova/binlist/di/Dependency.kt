package ru.kartyshova.binlist.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kartyshova.binlist.data.BinApi
import ru.kartyshova.binlist.database.Binbase
import ru.kartyshova.binlist.presentation.search.SearchViewModel
import ru.kartyshova.binlist.presentation.navigation.Navigator
import ru.kartyshova.binlist.data.BinRepository
import ru.kartyshova.binlist.data.BinRepositoryImpl
import ru.kartyshova.binlist.data.LocalBinRepository
import ru.kartyshova.binlist.data.LocalBinRepositoryImpl
import ru.kartyshova.binlist.domain.usecase.GetBinUseCase
import ru.kartyshova.binlist.domain.usecase.GetHistoryUseCase
import ru.kartyshova.binlist.domain.usecase.SaveBinUseCase

val dependency = module {
    single<BinApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return@single retrofit.create(BinApi::class.java)
    }

    single {
        val db = Room.databaseBuilder(androidApplication(), Binbase::class.java, "bins").build()
        db.binDao()
    }

    single<BinRepository> { BinRepositoryImpl(get()) }
    single<LocalBinRepository> { LocalBinRepositoryImpl(get()) }

    single { GetBinUseCase(get()) }
    single { SaveBinUseCase(get()) }
    single { GetHistoryUseCase(get()) }

    single { Navigator() }

    viewModel { SearchViewModel(get(), get(), get(), get()) }

}