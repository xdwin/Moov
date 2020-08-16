package com.xdwin.data.api

import com.xdwin.data.URLS.BASE_URL_MOVIEDB
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCreator {
    var retrofit = createRetrofit()

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIEDB)
            .client(createDefaultClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createDefaultClient(): OkHttpClient {
        val builder =  OkHttpClient.Builder()
        builder.interceptors().add(createInterceptor())
        return builder.build()
    }

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