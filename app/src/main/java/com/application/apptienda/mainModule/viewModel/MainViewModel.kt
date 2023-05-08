package com.application.apptienda.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.apptienda.common.entities.*
import com.application.apptienda.mainModule.model.MainInterator

class MainViewModel : ViewModel() {

    private var paymentMethodList: MutableList<PaymentMethodEntities>
    private var interactor: MainInterator
    private val _formDataUser = MutableLiveData<UserEntity>()
    private val formData: LiveData<UserEntity> = _formDataUser
    private val preferences: MutableLiveData<CheckoutResponseEntity>

    private val _formDataPayment = MutableLiveData<PaymentEntity>()
    private val formDataPayment: LiveData<PaymentEntity> = _formDataPayment


    fun saveFormDataUser(formData: UserEntity) {
        _formDataUser.value = formData
    }

    fun saveFormDataPayment(formData: PaymentEntity) {
        _formDataPayment.value = formData
    }

    init {
        paymentMethodList = mutableListOf()
        preferences =  MutableLiveData()
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

    fun getFormDataUser() : LiveData<UserEntity>{
        return formData
    }

    fun getFormDataPayment() : LiveData<PaymentEntity>{
        return formDataPayment
    }

    private fun loadPaymentMethod(){
        interactor.getPaymentMethod {
            paymentMethod.value = it
            paymentMethodList = it
        }
    }

    fun getPreferenceId(checkoutEntity: CheckoutEntity, callback: (String?) -> Unit){
        interactor.getCheckoutPayment(checkoutEntity) { preferences ->

            // Llamar al callback con preferences
            callback(preferences)

        }
    }
}