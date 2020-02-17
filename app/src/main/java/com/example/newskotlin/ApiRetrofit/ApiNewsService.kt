package com.example.newskotlin.ApiRetrofit

import com.example.newskotlin.Common.Common
import com.example.newskotlin.Model.RootObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsService {
    @GET("everything?")
    fun getNews(
        @Query("q") q: String = Common.search,
        @Query("language") language: String? = Common.language,
        @Query("page") page: Int = Common.page
    ): Deferred<RootObject>

    @GET("top-headlines")
    fun getTopNews(
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int,
        @Query("category") category: String
    ): Deferred<RootObject>

    companion object {
        operator fun invoke(): ApiNewsService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", Common.API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Common.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiNewsService::class.java)
        }
    }

}