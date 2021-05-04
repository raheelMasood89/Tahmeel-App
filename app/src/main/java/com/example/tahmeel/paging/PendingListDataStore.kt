package com.example.tahmeel.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.tahmeel.model.data.PendingOrder
import com.example.tahmeel.retrofit.RetroApiService
import okio.IOException
import javax.inject.Inject

class PendingListDataStore @Inject constructor(
    private val retroAPIService: RetroApiService
) : PagingSource<Int, PendingOrder>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PendingOrder> {
        try {
            val pageNumber = params.key ?: 0
            val response = retroAPIService.getPendingList(
                pageNumber.toString()
            )
            return LoadResult.Page(
                data = response.pendingOrders.toMutableList(),
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (response.pendingOrders.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, PendingOrder>): Int? {
        TODO("Not yet implemented")
    }
}
