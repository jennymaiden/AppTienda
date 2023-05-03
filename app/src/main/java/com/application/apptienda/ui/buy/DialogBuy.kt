package com.application.apptienda.ui.buy

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.application.apptienda.R
import com.application.apptienda.common.entities.UserEntity
import com.application.apptienda.mainModule.viewModel.MainViewModel

class DialogBuy: DialogFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var nombreInput: EditText
    private lateinit var tipoIdInput: Spinner
    private lateinit var identificacionInput: EditText
    private lateinit var direccionInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var ciudadInput: EditText

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
            dismiss()
            val dialogView = DialogPaymentMethod()
            dialogView.show(parentFragmentManager, "MyDialog")
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
        val formData = UserEntity(nombre, tipoId, identificacion, email, direccion, ciudad)
        viewModel.saveFormDataUser(formData)
    }

}
