package com.application.apptienda.ui.buy

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.application.apptienda.R
import com.application.apptienda.common.entities.PaymentEntity
import com.application.apptienda.common.entities.UserEntity
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.mainModule.viewModel.MainViewModel

class DialogPaymentMethod: DialogFragment() {

    private lateinit var dialogView: View
    private lateinit var viewModel: MainViewModel
    private lateinit var formDataUser: LiveData<UserEntity>
    private lateinit var numeroTarjetaInput: EditText
    private lateinit var tipoIdInput: Spinner
    private lateinit var identificacionInput: EditText
    private lateinit var nombreInput: EditText
    private lateinit var fechaInput: EditText
    private lateinit var codigoInput: EditText
    private lateinit var tipoTarjetaGroup: RadioGroup
    private lateinit var tipoTarjeta: RadioButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())

        dialogView = layoutInflater.inflate(R.layout.dialog_payment_method, null)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        numeroTarjetaInput = dialogView.findViewById(R.id.etNumeroTarjeta)
        nombreInput = dialogView.findViewById(R.id.etNombrePagador)
        fechaInput = dialogView.findViewById(R.id.etFechaExpiracion)
        codigoInput = dialogView.findViewById(R.id.etCodigo)
        tipoIdInput = dialogView.findViewById(R.id.id_type_spinner)
        identificacionInput = dialogView.findViewById(R.id.etNumeroId)
        tipoTarjetaGroup = dialogView.findViewById(R.id.groupRadio)

        formDataUser = (requireActivity() as MainActivity).mMainViewModel.getFormDataUser()

        nombreInput.setText(formDataUser.value?.nombre)
        identificacionInput.setText(formDataUser.value?.identificacion)

        val btnBuy: Button = dialogView.findViewById(R.id.btn_buy)
        btnBuy.setOnClickListener{
            // Iniciar el checkout de Mercado Pago
            saveFormData()
            dismiss()
        }
        dialog.setContentView(dialogView)
        return dialog
    }
    private fun saveFormData() {
        val nombre = nombreInput.text.toString()
        val tipoId = tipoIdInput.selectedItem.toString()
        val identificacion = identificacionInput.text.toString()
        val numeroTarjeta = numeroTarjetaInput.text.toString().toIntOrNull()
        val fecha = fechaInput.text.toString()
        val codigo = codigoInput.text.toString().toIntOrNull()
        val tipo = getTipoTarjeta()
        if (tipo == null || numeroTarjeta == null || codigo == null) {
            Toast.makeText(requireContext(), "Tenemos un error al ingresar los datos, intente de nuevo", Toast.LENGTH_SHORT).show()
            return
        }
        val formData = PaymentEntity(tipo, numeroTarjeta, nombre, fecha, codigo, tipoId,identificacion)
        Log.i("Informacion de pago ", formData.toString())
        viewModel.saveFormDataPayment(formData)
    }

    private fun getTipoTarjeta() : Int? {
        val radioButtonId = tipoTarjetaGroup.checkedRadioButtonId
        val radioButton = dialogView.findViewById<RadioButton>(radioButtonId)

        val selectedOption = when (radioButton.id) {
            R.id.rb_credito -> 1
            R.id.rb_debito -> 2
            else -> null
        }
        return selectedOption
    }
}