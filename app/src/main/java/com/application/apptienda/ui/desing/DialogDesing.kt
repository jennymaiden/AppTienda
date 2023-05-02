package com.application.apptienda.ui.desing

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.application.apptienda.R
import com.application.apptienda.ui.avatar.AvatarFragment
import com.application.apptienda.ui.buy.BuyFragment
import com.application.apptienda.common.utils.UtlisDesing

class DialogDesing: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val product = arguments?.getString("key")
        val dialogView = layoutInflater.inflate(R.layout.dialog_end_desing, null)
        val imageclothes: ImageView = dialogView.findViewById(R.id.img_desingEnd)
        Log.i("CARO", "DIALOG el produxcto es: $product")
        imageclothes.setImageResource(UtlisDesing.getDesing(product, requireContext()) )
        val btnAvatar:Button = dialogView.findViewById(R.id.btn_avatar)

        btnAvatar.setOnClickListener {
            val avatarFragment = AvatarFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, avatarFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            dismiss()
        }

        val btnBuy: Button = dialogView.findViewById(R.id.btn_buy)
        btnBuy.setOnClickListener{
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