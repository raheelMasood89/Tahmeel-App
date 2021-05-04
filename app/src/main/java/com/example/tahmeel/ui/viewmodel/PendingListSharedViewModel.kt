package com.example.tahmeel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.paging.PendingListDataStore
import com.example.tahmeel.usecase.SearchListUseCase
import com.example.tahmeel.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PendingListSharedViewModel @Inject constructor(
    private val useCase: SearchListUseCase,
    private val dataStore: PendingListDataStore
) : ViewModel() {

    private val _searchListItem: MutableLiveData<DataState<OrdersResponse>> =
        MutableLiveData()
    val searchListData: LiveData<DataState<OrdersResponse>> get() = _searchListItem

    val getPendingListData = Pager(PagingConfig(pageSize = 5)) {
        dataStore
    }.flow.cachedIn(viewModelScope)

    fun searchByString(query: String) = viewModelScope.launch() {
        useCase.execute(
            SearchListUseCase.Params(
                "0", query
            )
        ).collect {
            _searchListItem.value = it
        }
    }


}