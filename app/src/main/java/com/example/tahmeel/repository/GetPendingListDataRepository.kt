package com.example.tahmeel.repository

import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.retrofit.RetroApiService
import com.example.tahmeel.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPendingListDataRepository @Inject constructor(
    private val retroAPIService: RetroApiService,
) : GetPendingListRepo {

    override fun getPendingListList(
        dPage: String
    ): Flow<DataState<OrdersResponse>> = flow {
        retroAPIService.getPendingList(
            mPage = dPage,
        )
    }
}

