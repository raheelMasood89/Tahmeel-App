package com.example.tahmeel.di

import com.example.tahmeel.repository.GetPendingListDataRepository
import com.example.tahmeel.repository.GetPendingListRepo
import com.example.tahmeel.repository.GetSearchItemDataRepository
import com.example.tahmeel.repository.GetSearchItemRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindPopularRepo(getPendingListDataRepository: GetPendingListDataRepository): GetPendingListRepo

    @Singleton
    @Binds
    abstract fun bindSearchRepo(searchItemRepo: GetSearchItemDataRepository): GetSearchItemRepo
}