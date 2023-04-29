package com.application.apptienda.ui.color

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentColorBinding
import com.application.apptienda.ui.desing.DesingFragment
import com.application.apptienda.utils.Constants
import com.application.apptienda.utils.UtlisDesing

class ColorFragment : Fragment() {

    private var _binding: FragmentColorBinding? = null
    private val binding get() = _binding!!

    private lateinit var myType: LiveData<String>
    private lateinit var imageclothes: ImageView
    private var PRICE_BASE: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentColorBinding.inflate(inflater, container, false)
        imageclothes = binding.imgColor
        val txtPrice: TextView = binding.txtPrice
        myType = (requireActivity() as MainActivity).productViewModel.getType()
        (requireActivity() as MainActivity).productViewModel.getType().observe(viewLifecycleOwner) { type ->
            when (type) {
                "shirt" -> {
                    imageclothes.setImageResource(R.mipmap.camisa_negra)
                    PRICE_BASE = Constants.PRICE_SHIRT
                }

                "sweater" -> {
                    imageclothes.setImageResource(R.mipmap.buzo_negro)
                    PRICE_BASE = Constants.PRICE_SWEATER
                }
            }
        }
        (requireActivity() as MainActivity).productViewModel.getPrice().observe(viewLifecycleOwner) { price ->
            txtPrice.text = "$${price}"
        }
        initUI()
        return binding.root
    }

    private fun initUI() {
        val btnNextColor: Button = binding.btnNextColor
        val btnWhiteColor: Button = binding.btnBlanco
        val btnBlackColor: Button = binding.btnNegro
        val btnBlueColor: Button = binding.btnAzul
        val btnRedColor: Button = binding.btnRojo
        val btnBack: Button = binding.btnBack

        btnNextColor.setOnClickListener {
            val desingFragment = DesingFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, desingFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnWhiteColor.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setColor("white")
            (requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE+Constants.PRICE_COLOR_WHITE)
            imageclothes.setImageResource(UtlisDesing.getChageColor("White", myType.value))
        }

        btnBlackColor.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setColor("black")
            (requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE+Constants.PRICE_COLOR_BLACK)
            imageclothes.setImageResource(UtlisDesing.getChageColor("Black", myType.value))
        }

        btnBlueColor.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setColor("blue")
            (requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE+Constants.PRICE_COLOR_BLUE)
            imageclothes.setImageResource(UtlisDesing.getChageColor("Blue", myType.value))
        }

        btnRedColor.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setColor("red")
            (requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE+Constants.PRICE_COLOR_RED)
            imageclothes.setImageResource(UtlisDesing.getChageColor("Red", myType.value))
        }

        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}

