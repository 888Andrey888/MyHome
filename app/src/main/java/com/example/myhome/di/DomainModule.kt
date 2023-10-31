package com.example.myhome.di

import com.example.myhome.domain.repository.RetrofitRepository
import com.example.myhome.domain.usecase.GetAllNotesUseCase
import com.example.myhome.domain.usecase.GetCamerasUseCase
import com.example.myhome.domain.usecase.GetDoorsUseCase
import com.example.myhome.domain.usecase.InsertNoteUseCase
import com.example.myhome.domain.usecase.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCameraUseCase(retrofitRepository: RetrofitRepository) =
        GetCamerasUseCase(retrofitRepository)

    @Provides
    fun provideGetDoorsUseCase(retrofitRepository: RetrofitRepository) =
        GetDoorsUseCase(retrofitRepository)

    @Provides
    fun provideGetAllNotesUseCase(retrofitRepository: RetrofitRepository) =
        GetAllNotesUseCase(retrofitRepository)

    @Provides
    fun provideInsertNoteUseCase(retrofitRepository: RetrofitRepository) =
        InsertNoteUseCase(retrofitRepository)

    @Provides
    fun provideUpdateNoteUseCase(retrofitRepository: RetrofitRepository) =
        UpdateNoteUseCase(retrofitRepository)

}