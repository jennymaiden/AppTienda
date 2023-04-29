package com.application.apptienda.ui.buy
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError

interface PaymentResultListener {
    fun onPaymentSuccess(payment: Payment)
    fun onPaymentError(mercadoPagoError: MercadoPagoError)
    fun onPaymentCancelled()
}