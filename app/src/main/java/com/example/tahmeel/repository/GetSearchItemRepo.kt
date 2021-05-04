package com.example.tahmeel.repository

import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.util.DataState
import kotlinx.coroutines.flow.Flow

interface GetSearchItemRepo {

    fun getSearchItem(
        dPage: String , mSearch: String
    ): Flow<DataState<OrdersResponse>>
}


