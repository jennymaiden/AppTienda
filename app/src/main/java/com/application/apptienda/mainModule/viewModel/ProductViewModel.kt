package com.application.apptienda.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.apptienda.mainModule.model.MainInterator

class ProductViewModel : ViewModel() {

    private val type:  MutableLiveData<String> = MutableLiveData()
    private val color:  MutableLiveData<String> = MutableLiveData()
    private val design:  MutableLiveData<Int> = MutableLiveData()
    private val price:  MutableLiveData<Int> = MutableLiveData()
    private val genero:  MutableLiveData<String> = MutableLiveData()
    private val perfil:  MutableLiveData<Int> = MutableLiveData()
    private val hair:  MutableLiveData<Int> = MutableLiveData()
    private val hairColor:  MutableLiveData<String> = MutableLiveData()
    private val productAndPerfil: MutableLiveData<String> = MutableLiveData()
    private val size: MutableLiveData<String> = MutableLiveData()
    private val quantity: MutableLiveData<Int> = MutableLiveData()

    fun getType(): LiveData<String> {
        return type
    }

    fun setType(newValue: String) {
        type.value = newValue
    }

    fun getColor(): LiveData<String> {
        return color
    }

    fun setColor(newValue: String) {
        color.value = newValue
    }

    fun getPrice(): LiveData<Int> {
        return price
    }

    fun setPrice(newValue: Int) {
        price.value = newValue
    }

    fun getDesign(): LiveData<Int> {
        return design
    }

    fun setDesign(newValue: Int) {
        design.value = newValue
    }

    fun getGenero(): LiveData<String> {
        return genero
    }

    fun setGenero(newValue: String) {
        genero.value = newValue
    }

    fun getPerfil(): LiveData<Int> {
        return perfil
    }

    fun setPerfil(newValue: Int) {
        perfil.value = newValue
    }

    fun getHair(): LiveData<Int> {
        return hair
    }

    fun setHair(newValue: Int) {
        hair.value = newValue
    }

    fun getHairColor(): LiveData<String> {
        return hairColor
    }

    fun setHairColor(newValue: String) {
        hairColor.value = newValue
    }

    fun getProductAndPerfil(): LiveData<String> {
        return productAndPerfil
    }

    fun setProductAndPerfil(newValue: String) {
        productAndPerfil.value = newValue
    }

    fun getSize(): LiveData<String> {
        return size
    }

    fun setSize(newValue: String) {
        size.value = newValue
    }

    fun getQuantity(): LiveData<Int> {
        return quantity
    }

    fun setQuantity(newValue: Int) {
        quantity.value = newValue
    }

}