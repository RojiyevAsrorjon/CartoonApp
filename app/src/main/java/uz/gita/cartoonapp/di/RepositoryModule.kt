package uz.gita.cartoonapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.cartoonapp.domen.repositories.ContentRepository
import uz.gita.cartoonapp.domen.repositories.RoomRepository
import uz.gita.cartoonapp.domen.repositories.impl.ContentRepositoryImpl
import uz.gita.cartoonapp.domen.repositories.impl.RoomRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getContentRepository(contentRepositoryImpl: ContentRepositoryImpl) : ContentRepository

    @Binds
    @Singleton
    fun getRoomRepository(roomRepositoryImpl: RoomRepositoryImpl) : RoomRepository
}