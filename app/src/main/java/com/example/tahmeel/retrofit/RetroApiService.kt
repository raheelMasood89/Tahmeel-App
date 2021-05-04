package com.example.tahmeel.retrofit

import com.example.tahmeel.model.response.OrdersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroApiService {

    @GET("orders/pending")
    suspend fun getPendingList(@Query("page") mPage: String?): OrdersResponse

    @GET("orders/pending")
    suspend fun getPendingSearchList(@Query("page") mPage: String?, @Query("search") mSearch: String): OrdersResponse
}