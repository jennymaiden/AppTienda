package com.application.apptienda.ui.buy

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.application.apptienda.R

class DialogBuy: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())

        val dialogView = layoutInflater.inflate(R.layout.dialog_form_buy, null)

        val btnBuy: Button = dialogView.findViewById(R.id.btn_buy)
        btnBuy.setOnClickListener{
            // Iniciar el checkout de Mercado Pago

            val buyFragment = BuyFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, buyFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            dismiss()
        }
        dialog.setContentView(dialogView)
        return dialog
    }

}
