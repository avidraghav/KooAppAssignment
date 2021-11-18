package com.example.kooappassignment.di

import com.example.kooappassignment.data.network.SampleApi
import com.example.kooappassignment.data.network.RemoteDataSource
import com.example.kooappassignment.repository.AppRepository
import com.example.kooappassignment.ui.AppViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSampleApi(
        remoteDataSource: RemoteDataSource
    ): SampleApi {
        return remoteDataSource.buildApi(SampleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideViewModel(appRepository: AppRepository): AppViewModel {
        return AppViewModel(appRepository)
    }

}