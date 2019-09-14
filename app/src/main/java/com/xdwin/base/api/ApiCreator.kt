package com.xdwin.base.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCreator {
    var retrofit = createRetrofit()

    // todo change your base url here
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(createDefaultClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createDefaultClient(): OkHttpClient {
        val builder =  OkHttpClient.Builder()
        builder.interceptors().add(createInterceptor())
        builder.networkInterceptors().add(StethoInterceptor())
        return builder.build()
    }

    // todo change your api key here
    private fun createInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("api_key", "355c2729253ec43ce8eeafc5fecff1dd")
                    .build()
                return chain.proceed(request)
            }
        }
    }
}