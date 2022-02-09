package uz.gita.cartoonapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.cartoonapp.data.local.AppDatabase
import uz.gita.cartoonapp.data.local.daos.CartoonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabase {

    @Provides
    @Singleton
    fun getRoomDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.init(context)

    @[Provides Singleton]
    fun getRoomDao(database: AppDatabase): CartoonDao = database.dao()
}