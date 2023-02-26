package com.assignment.neosoftassignment.di

import com.assignment.neosoftassignment.Constants
import com.assignment.neosoftassignment.model.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
      /*  val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)*/

        val okHttpClient: OkHttpClient = Builder()
            .readTimeout(80, TimeUnit.SECONDS)
            .connectTimeout(80, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesFakerAPI(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}