package com.application.apptienda.ui.buy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentBuyBinding
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.common.utils.Constants
import com.application.apptienda.common.constans.Urls
import com.application.apptienda.ui.desing.DialogDesing
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.internal.datasource.MercadoPagoPaymentConfiguration
import com.mercadopago.android.px.internal.features.review_and_confirm.components.payment_method.PaymentMethodComponent
import java.math.BigDecimal
import kotlin.properties.Delegates

class BuyFragment : Fragment(), DialogListener {

    private var _binding: FragmentBuyBinding? = null

    private val binding get() = _binding!!

    private lateinit var imageStyle: ImageView
    private lateinit var productAndPerfil: LiveData<String>
    private lateinit var size: LiveData<String>
    private lateinit var quantity2: LiveData<Int>
    private var myPrice by Delegates.notNull<Int>()
    private lateinit var url: String
    private lateinit var preferencia: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        Log.i("CARO xxx onCreateView ", "Imprime esto")
        initModel()
        initView()
        initComponent()
        return binding.root
    }

    private fun initModel() {
        productAndPerfil = (requireActivity() as MainActivity).productViewModel.getProductAndPerfil()
        url = productAndPerfil?.value ?: "GIRL_PERFIL1_HAIR1_RED_SWEATER_BLACK_ANIMADO1"
        myPrice = (requireActivity() as MainActivity).productViewModel.getPrice().value!!
        val txtPrice: TextView = binding.lblPrice
        txtPrice.setText("$${myPrice}")

        (requireActivity() as MainActivity).productViewModel.getQuantity().observe(viewLifecycleOwner) { quality ->
            val priceNew = myPrice * quality
            txtPrice.text = "$${priceNew}"
            if (priceNew != null) {
                (requireActivity() as MainActivity).productViewModel.setPrice(priceNew)
            }
        }
    }

    private fun initView() {
        imageStyle = binding.imgDesingEnd
        Log.i("CARO", "BuyFragment la constante es: ${url}")
        val urlFile = Urls::class.java.getField(url).get(null) as String
        Glide.with(requireContext()).load(urlFile)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageStyle)

        //Buscar los metodos de pago

    }

    private fun initComponent() {
        val radioGroup = binding.groupRadio
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_s -> {
                    //Toast.makeText(requireContext(), "Selected: S", Toast.LENGTH_SHORT).show()
                    (requireActivity() as MainActivity).productViewModel.setSize("S")
                }
                R.id.rb_m -> {
                    //Toast.makeText(requireContext(), "Selected: M", Toast.LENGTH_SHORT).show()
                    (requireActivity() as MainActivity).productViewModel.setSize("M")
                }
                R.id.rb_l -> {
                    //Toast.makeText(requireContext(), "Selected: L", Toast.LENGTH_SHORT).show()
                    (requireActivity() as MainActivity).productViewModel.setSize("L")
                }
            }
        }

        val etCantidad = binding.etCantidad
        etCantidad.minValue = 0 // Valor mínimo
        etCantidad.maxValue = 10 // Valor máximo
        etCantidad.value = 0 // Valor inicial

        /*etCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString().trim()
                if (inputText.isNotEmpty()) {
                    val numericValue = inputText.toDouble()
                    //Toast.makeText(requireContext(), "Selected: $numericValue", Toast.LENGTH_SHORT).show()
                    (requireActivity() as MainActivity).productViewModel.setQuantity(numericValue.toInt())
                    // Do something with the numeric value
                }
            }
        })*/

        etCantidad.setOnValueChangedListener { picker, oldVal, newVal ->
            // Aquí puedes realizar acciones basadas en el nuevo valor seleccionado
            (requireActivity() as MainActivity).productViewModel.setQuantity(newVal)
        }

        val btnBuy: Button = binding.btnBuy

        btnBuy.setOnClickListener{
            val bundle = Bundle().apply {
                putString("title", (requireActivity() as MainActivity).productViewModel.getType().value!!)
                putString("description", (requireActivity() as MainActivity).productViewModel.getColor().value!!)
                putInt("quantity", (requireActivity() as MainActivity).productViewModel.getQuantity().value!!)
                putInt("unit_price", (requireActivity() as MainActivity).productViewModel.getPrice().value!!)
            }
            val dialogView = DialogBuy()
            dialogView.dialogListener = this
            dialogView.arguments = bundle
            dialogView.show(parentFragmentManager, "MyDialog")

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startMercadoPagoCheckout() {
        // Crea una instancia de MercadoPagoCheckout
        val mercadoPagoCheckout = MercadoPagoCheckout.Builder(Constants.PUBLIC_KEY, "<YOUR CHECKOUT PREFERENCE ID>")
            .build()

        // Inicia el proceso de pago
        /*try {
            startActivityForResult(mercadoPagoCheckout.intent, MercadoPagoCheckout.PAYMENT_RESULT_CODE)
        } catch (e: CheckoutPreferenceExpiredException) {
            // Maneja la excepción de preferencia de pago expirada
        }*/
    }
    fun setPaymentResultListener(mainActivity: MainActivity) {
        Toast.makeText(requireContext(), "setPaymentResultListener", Toast.LENGTH_SHORT).show()
    }

    // Maneja la respuesta del proceso de pago
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
            when (resultCode) {
                MercadoPagoCheckout.PAYMENT_RESULT_CODE -> {
                    // El pago fue exitoso
                }
                MercadoPagoCheckout.SESSION_EXPIRED_RESULT_CODE -> {
                    // El usuario canceló el pago
                }
            }
        }
    }

    override fun onDialogDataPassed(data: String) {
        Log.i("se cerroe el diaglos y tenemos la preferencia ", "Imprime esto")
        preferencia = data

    }

    override fun onDialogClosed() {
        Log.i("BuyFragment del fragmento", "El diálogo se ha cerrado")
        Log.i("onDialogClosed ::::::: preferencia", preferencia)

        val mercadoPagoCheckout = MercadoPagoCheckout.Builder(Constants.PUBLIC_KEY, preferencia)
            .build()
        mercadoPagoCheckout.startPayment(requireContext(), Constants.REQUEST_CODE)
    }

}