package com.example.tahmeel.model.data

import com.google.gson.annotations.SerializedName

data class PendingOrder(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("load_ref")
    val loadRef: String,
    @SerializedName("order_number")
    val orderNumber: String,
    @SerializedName("order_price_formatted")
    val orderPriceFormatted: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("tahmeel_fee_in_cents")
    val tahmeelFeeInCents: Int
)