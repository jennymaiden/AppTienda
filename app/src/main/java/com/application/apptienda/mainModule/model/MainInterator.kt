package com.application.apptienda.mainModule.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.application.apptienda.StoreApplication
import com.application.apptienda.common.entities.CheckoutEntity
import com.application.apptienda.common.entities.CheckoutResponseEntity
import com.application.apptienda.common.entities.PaymentMethodEntities
import com.application.apptienda.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject



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

    fun getCheckoutPayment(checkoutEntity: CheckoutEntity, callback: (String?) -> Unit ){
        val urlCheckoutPayment = "${Constants.URL_MERCADOPAGO}/checkout/preferences?access_token=${Constants.ACCESS_TOKEN}"

        val jsonParams = JSONObject(checkoutEntity.toString())
        Log.i("CARO EL JSON DE ENVIO getCheckoutPayment", jsonParams.toString())

        val jsonObjectRequest = object : JsonObjectRequest(Method.POST, urlCheckoutPayment, jsonParams, { response ->
            Log.i("response getCheckoutPayment", response.toString())
            val preferenceId = response.optString("id")

            //val mutableListType = object : TypeToken<MutableLiveData<CheckoutResponseEntity>>(){}.type
            //val result = Gson().fromJson<MutableLiveData<CheckoutResponseEntity>>(response.toString(), mutableListType)
            callback(preferenceId)
        }, {
            it.printStackTrace()

            Log.i("ERROR", "AL CONSUMIR EL API DE CHECK DE PAGOS")
            Log.i("ERROR", it.toString())
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                params["Content-Type"] = "application/json"
                return params
            }
        }

        StoreApplication.paymentMethodAPI.addToRequestQueue(jsonObjectRequest)
    }
}