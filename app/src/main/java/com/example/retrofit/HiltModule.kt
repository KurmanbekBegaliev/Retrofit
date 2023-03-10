package com.example.retrofit

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun getApi(): CalculateApi {
        return getRetrofit().create(CalculateApi::class.java)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    @Provides
    fun getClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getInterceptor())
            .build()
    }

    @Provides
    fun getInterceptor() : HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
    }


//    @Provides
//    @StringBuilderOne
//    fun getString(): java.lang.StringBuilder {
//        return StringBuilder("First")
//    }
//
//    @Provides
//    @StringBuilderTwo
//    fun getString2(): java.lang.StringBuilder {
//        return StringBuilder("Second")
//    }
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class StringBuilderOne
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class StringBuilderTwo

}
