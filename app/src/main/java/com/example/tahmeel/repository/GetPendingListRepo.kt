package com.example.tahmeel.repository

import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.util.DataState
import kotlinx.coroutines.flow.Flow

interface GetPendingListRepo {

    fun getPendingListList(
        dPage: String
    ): Flow<DataState<OrdersResponse>>
}


