package com.easycoder.product_view_mvvm.core.data.network

import android.content.Context
import com.easycoder.product_view_mvvm.BuildConfig

import com.google.gson.GsonBuilder


import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by HM SHOHRAB on 02,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */
object RestApiClient {
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private var loggingInterceptor: HttpLoggingInterceptor? = null
    private var rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())


    fun getRetrofit(context: Context): Retrofit {
        if (retrofit == null) {
            retrofit = create(context)
        }
        return retrofit!!
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun create(context: Context): Retrofit {
        // Level.BODY
        /*shows all the information of a request. We just want to see info when app
        * is in debug mode*/
        loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            loggingInterceptor!!.level = HttpLoggingInterceptor.Level.BODY
        else loggingInterceptor!!.level = HttpLoggingInterceptor.Level.BASIC

        okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor!!)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .client(okHttpClient!!)
            .build()
    }


}