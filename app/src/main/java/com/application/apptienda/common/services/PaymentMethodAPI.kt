package com.application.apptienda.common.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class PaymentMethodAPI constructor(context : Context) {
    companion object{
        @Volatile
        private var INSTANCE:PaymentMethodAPI? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this) {
            INSTANCE?: PaymentMethodAPI(context).also { INSTANCE = it }
        }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}