package com.application.apptienda.common.entities

data class PaymentMethodEntities(val id: String, val name: String, val payment_type_id: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentMethodEntities

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}