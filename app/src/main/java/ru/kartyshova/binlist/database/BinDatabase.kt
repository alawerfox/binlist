package ru.kartyshova.binlist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [BinEntity::class])
internal abstract class Binbase : RoomDatabase() {
    abstract fun binDao(): BinDao
}