package com.application.apptienda.ui.buy

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.application.apptienda.R
import com.application.apptienda.common.entities.CheckoutEntity
import com.application.apptienda.common.entities.CheckoutResponseEntity
import com.application.apptienda.common.entities.UserEntity
import com.application.apptienda.common.utils.Constants
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.mainModule.viewModel.MainViewModel
import com.mercadopago.android.px.core.MercadoPagoCheckout
import java.math.BigDecimal

class DialogBuy: DialogFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var nombreInput: EditText
    private lateinit var tipoIdInput: Spinner
    private lateinit var identificacionInput: EditText
    private lateinit var direccionInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var ciudadInput: EditText
    private lateinit var user : UserEntity
    private var dialogListener: DialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())

        val dialogView = layoutInflater.inflate(R.layout.dialog_form_buy, null)
        nombreInput = dialogView.findViewById(R.id.etNombre)
        tipoIdInput = dialogView.findViewById(R.id.id_type_spinner)
        identificacionInput = dialogView.findViewById(R.id.etNumeroId)
        emailInput = dialogView.findViewById(R.id.etEmail)
        direccionInput = dialogView.findViewById(R.id.etAddress)
        ciudadInput = dialogView.findViewById(R.id.etCity)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val btnBuy: Button = dialogView.findViewById(R.id.btn_buy)
        btnBuy.setOnClickListener{
            saveFormData()
            //Crear la preferencia de pago
            getPreferenceApi(){
                Log.i("getPreferenceApi preferencia ::::::: ",it.toString() )
                dialogListener?.onDialogDataPassed(it!!)

                closeDialog()/*val bundle = Bundle().apply {
                    putString("preferenciaId", it)
                }
                val dialogView = DialogPaymentMethod()
                dialogView.arguments = bundle
                dialogView.show(parentFragmentManager, "MyDialog")*/
            }

        }
        dialog.setContentView(dialogView)
        return dialog
    }

    private fun saveFormData() {
        val nombre = nombreInput.text.toString()
        val tipoId = tipoIdInput.selectedItem.toString()
        val identificacion = identificacionInput.text.toString()
        val email = emailInput.text.toString()
        val direccion = direccionInput.text.toString()
        val ciudad = ciudadInput.text.toString()
        user = UserEntity(nombre, tipoId, identificacion, email, direccion, ciudad)
        viewModel.saveFormDataUser(user)
    }

    private fun getPreferenceApi(callback: (String?) -> Unit){
        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val quantity = arguments?.getInt("quantity")
        val price = arguments?.getInt("unit_price")

        val checkout = CheckoutEntity(title!!, description!!, quantity!! , BigDecimal(price!!), user.email, user.identificacion, user.tipoId, user.nombre, user.direccion )
        //Log.i("getPreferenceApi El checkout ",checkout.toString() )
        (requireActivity() as MainActivity).mMainViewModel.getPreferenceId(checkout){it ->
            Log.i("getPreferenceApi preferencia ",it.toString() )
            callback(it.toString())
        }
    }

    private fun closeDialog() {
        dialogListener?.onDialogClosed()
        Log.i("Entra ","closeDialog" )
        dismiss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            if (context is DialogListener) {
                dialogListener = context
            }
            Log.i("CARO xxx onAttach ", "Imprime esto")
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DialogListener")
        }
    }


}
