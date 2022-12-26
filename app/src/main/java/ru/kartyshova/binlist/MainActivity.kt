package ru.kartyshova.binlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import ru.kartyshova.binlist.di.dependency
import ru.kartyshova.binlist.presentation.navigation.Navigator

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity.applicationContext)
            modules(dependency)
        }

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navigator.bind(navHostFragment.navController)
    }

    override fun onDestroy() {
        navigator.unbind()
        stopKoin()
        super.onDestroy()
    }
}