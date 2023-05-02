package com.application.apptienda.mainModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.application.apptienda.StoreApplication
import com.application.apptienda.common.entities.PaymentMethodEntities
import com.application.apptienda.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainInterator {

    fun getPaymentMethod(callback: (MutableList<PaymentMethodEntities>) -> Unit ) {
        val urlPaymentMethod = "${Constants.URL_MERCADOPAGO}/v1/payment_methods?access_token=${Constants.ACCESS_TOKEN}"
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, urlPaymentMethod, null, { response ->
            Log.i("Response", response.toString())
            val mutableListType = object : TypeToken<MutableList<PaymentMethodEntities>>(){}.type
            val paymentMethodList = Gson().fromJson<MutableList<PaymentMethodEntities>>(response.toString(), mutableListType)
            Log.i("paymentMethodList", paymentMethodList.toString())
            callback(paymentMethodList)

        }, {
            it.printStackTrace()
        })
        StoreApplication.paymentMethodAPI.addToRequestQueue(jsonObjectRequest)

    }
}