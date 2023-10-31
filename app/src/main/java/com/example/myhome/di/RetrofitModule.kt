package com.example.myhome.di

import com.example.myhome.data.local.storage.RoomStorage
import com.example.myhome.data.remote.network.ApiService
import com.example.myhome.data.remote.network.RetrofitClient
import com.example.myhome.data.remote.storage.RetrofitStorage
import com.example.myhome.data.remote.storage.RetrofitStorageImpl
import com.example.myhome.data.repository.RetrofitRepositoryImpl
import com.example.myhome.domain.repository.RetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitClient() = RetrofitClient()

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: RetrofitClient) = retrofitClient.createApiService()

    @Provides
    @Singleton
    fun provideRetrofitStorage(apiService: ApiService): RetrofitStorage {
        return RetrofitStorageImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofitRepository(
        retrofitStorage: RetrofitStorage,
        roomStorage: RoomStorage
    ): RetrofitRepository {
        return RetrofitRepositoryImpl(retrofitStorage, roomStorage)
    }

}