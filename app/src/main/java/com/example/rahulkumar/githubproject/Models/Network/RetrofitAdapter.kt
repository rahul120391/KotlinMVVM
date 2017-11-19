package com.example.rahulkumar.githubproject.Models.Network

import com.example.rahulkumar.mvvmarchitecture.Utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by rahulkumar on 09/11/17.
 */
class RetrofitAdapter {

    companion object {
          fun create(): RetrofitServiceAnnotator {
              val interceptor = HttpLoggingInterceptor()
              interceptor.level = HttpLoggingInterceptor.Level.BODY
              val client1 = OkHttpClient.Builder().addInterceptor(interceptor)
              val retorfitAdapter = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                      .addConverterFactory(MoshiConverterFactory.create())
                      .client(client1.build())
                      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                      .build()
              return retorfitAdapter.create(RetrofitServiceAnnotator::class.java)
          }
    }
}