package com.fikri.submissiongithubuserbfaa.api

import com.fikri.submissiongithubuserbfaa.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val TOKEN = "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"
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor()
                    .setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    )
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                    it.proceed(
                        it.request().newBuilder()
//                            .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}").build()
                            .addHeader("Authorization", "token $TOKEN").build()
                    )
                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}