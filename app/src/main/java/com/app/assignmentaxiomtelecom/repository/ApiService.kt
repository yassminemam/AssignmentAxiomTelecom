package com.app.assignmentaxiomtelecom.repository

import CatalogItem
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.ArrayList
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("b/5f3a3fcf4d939910361666fe/latest")
    fun getCatalogItems(@Header("secret-key") key: String): Observable<ArrayList<CatalogItem>>


    companion object {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()


            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.jsonbin.io/")
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}