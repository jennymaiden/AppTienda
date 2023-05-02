package com.application.apptienda.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.apptienda.common.entities.PaymentMethodEntities
import com.application.apptienda.mainModule.model.MainInterator

class MainViewModel : ViewModel() {

    private var paymentMethodList: MutableList<PaymentMethodEntities>
    private var interactor: MainInterator

    init {
        paymentMethodList = mutableListOf()
        interactor = MainInterator()
    }

    private val paymentMethod: MutableLiveData<MutableList<PaymentMethodEntities>> by lazy {
        MutableLiveData<MutableList<PaymentMethodEntities>>().also {
            loadPaymentMethod()
        }
    }

    fun getPaymentMethod(): LiveData<MutableList<PaymentMethodEntities>> {
        return paymentMethod
    }

    private fun loadPaymentMethod(){
        interactor.getPaymentMethod {
            paymentMethod.value = it
            paymentMethodList = it
        }
    }
}