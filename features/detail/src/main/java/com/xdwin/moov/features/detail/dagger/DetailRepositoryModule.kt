package com.xdwin.moov.features.detail.dagger

import com.xdwin.data.api.ApiCreator
import com.xdwin.moov.features.detail.detail.DetailRepository
import com.xdwin.moov.features.detail.detail.DetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DetailRepositoryModule {
    @Provides
    fun provideDetailRepository(retrofit: Retrofit): DetailRepository {
        return DetailRepositoryImpl(retrofit)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return ApiCreator.retrofit
    }
}