package com.lubnamariyam.zapp.network

import com.lubnamariyam.zapp.model.FeedData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitService {

    interface ApiService {
        @GET("/posts")
        suspend fun getProduct(): FeedData


        companion object {
            private var apiService: ApiService? = null
            private var baseUrl = "https://jsonplaceholder.typicode.com/"
            fun getInstance(): ApiService {
                if (apiService == null) {
                    apiService = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiService::class.java)
                }
                return apiService!!
            }
        }
    }

}