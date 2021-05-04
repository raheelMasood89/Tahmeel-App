package com.example.tahmeel.usecase

import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.repository.GetSearchItemRepo
import com.example.tahmeel.retrofit.NetworkHelper
import com.example.tahmeel.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchListUseCase @Inject constructor(private val searchListRepo: GetSearchItemRepo) :
    NetworkHelper<SearchListUseCase.Params, OrdersResponse>() {

    data class Params constructor(
        val dPage: String,
        val dSearch: String
    )

    override fun buildUseCase(parameters: Params?): Flow<DataState<OrdersResponse>> {
        return with(parameters!!)
        {
            searchListRepo.getSearchItem(
                dPage = dPage,
                mSearch = dSearch
            )
        }
    }
}