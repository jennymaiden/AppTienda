package com.application.apptienda.common.entities

data class UserEntity(
    val nombre: String,
    val tipoId: String,
    val identificacion: String,
    val email: String,
    val direccion: String,
    val ciudad: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}