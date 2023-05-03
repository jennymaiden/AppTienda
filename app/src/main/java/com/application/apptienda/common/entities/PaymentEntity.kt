package com.application.apptienda.common.entities

data class PaymentEntity(
    val tipoTarjeta: Int,
    val numero: Int,
    val nombre: String,
    val fechaExpiracion: String,
    val codigo: Int,
    val tipoId: String,
    val numeroId: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentEntity

        if (numero != other.numero) return false

        return true
    }

    override fun hashCode(): Int {
        return numero.hashCode()
    }
}