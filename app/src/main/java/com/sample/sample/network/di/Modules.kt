package com.sample.sample.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sample.sample.network.service.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        converterFactory: Converter.Factory,
        @BaseUrlQualifier baseUrl:String,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        }.build()

    @Singleton
    @Provides
    @BaseUrlQualifier
    fun provideBaseUrl() = BASEURL

    @Singleton
    @Provides
    fun provideGsonInstance(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGSONConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideHoldingApiService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

    companion object {
        const val CONNECTION_TIMEOUT: Long = 100
        const val BASEURL = "https://my-json-server.typicode.com/"
    }
}

