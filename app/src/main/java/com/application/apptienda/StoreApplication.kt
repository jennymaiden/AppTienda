package com.application.apptienda

import android.app.Application
import com.application.apptienda.common.services.PaymentMethodAPI

class StoreApplication : Application() {

    companion object{
        lateinit var paymentMethodAPI: PaymentMethodAPI
    }

    override fun onCreate() {
        super.onCreate()
        //Volley
        paymentMethodAPI = PaymentMethodAPI.getInstance(this)
    }
}