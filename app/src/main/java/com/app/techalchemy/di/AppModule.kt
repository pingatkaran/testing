package com.app.techalchemy.di

import android.app.Application
import com.app.techalchemy.network.APIService
import com.app.techalchemy.network.NetworkConnectionInterceptor
import com.app.techalchemy.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUrl() = "https://api.sheety.co/"

    @Provides
    @Singleton
    fun providePreferences(app: Application): Preferences =
        Preferences(app)

    @Provides
    @Singleton
    fun providesApiService(url:String) : APIService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
}
