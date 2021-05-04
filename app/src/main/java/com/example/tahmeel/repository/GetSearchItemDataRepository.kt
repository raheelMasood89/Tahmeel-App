package com.example.tahmeel.repository

import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.retrofit.RetroApiService
import com.example.tahmeel.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchItemDataRepository @Inject constructor(
    private val retroAPIService: RetroApiService,
) : GetSearchItemRepo {

    override fun getSearchItem(
        dPage: String, mSearch: String
    ): Flow<DataState<OrdersResponse>> = flow {
        emit(DataState.Success(retroAPIService.getPendingSearchList(
            mPage = dPage,
            mSearch = mSearch
        )))
    }
}

