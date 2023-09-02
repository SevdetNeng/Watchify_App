package com.sevdetneng.watchify.di

import com.sevdetneng.watchify.network.TmdbApi
import com.sevdetneng.watchify.repository.ApiRepository
import com.sevdetneng.watchify.repository.FbRepository
import com.sevdetneng.watchify.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTmdbApi() : TmdbApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TmdbApi::class.java)

    @Singleton
    @Provides
    fun provideApiRepository(api : TmdbApi) = ApiRepository(api)

    @Singleton
    @Provides
    fun provideFbRepository() = FbRepository()
}