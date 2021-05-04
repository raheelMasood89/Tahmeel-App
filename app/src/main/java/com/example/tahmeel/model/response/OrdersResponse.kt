package com.example.tahmeel.model.response
import com.example.tahmeel.model.data.PendingOrder
import com.google.gson.annotations.SerializedName

data class OrdersResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pending_orders")
    val pendingOrders: List<PendingOrder>
)