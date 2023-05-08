package com.application.apptienda.common.entities

import com.mercadopago.android.px.model.Identification
import java.math.BigDecimal

data class CheckoutEntity(
    val title: String,
    val description: String,
    val quantity: Int,
    val unit_price: BigDecimal,
    val email: String,
    val identification: String,
    val typeIdentification: String,
    val nameUser: String,
    val address: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheckoutEntity

        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    override fun toString(): String {
        val json: String = "{\"items\": [{\"title\": \"${title}\",\"description\": \"${description}\",\"quantity\": ${quantity},\"currency_id\": \"COP\",\"unit_price\": ${unit_price}}]" +
                ",\"payer\": {\"email\": \"${email}\", \"identification\": {\"number\": \"${identification}\", \"type\": \"${typeIdentification}\" }, \"name\": \"${nameUser}\"," +
                " \"address\": { \"street_name\": \"${address}\" } } }"
        return json
    }

}