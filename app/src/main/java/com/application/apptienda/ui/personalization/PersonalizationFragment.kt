package com.application.apptienda.ui.personalization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentPersonalizationBinding
import com.application.apptienda.ui.buy.BuyFragment
import com.application.apptienda.utils.Urls
import com.application.apptienda.utils.UtlisDesing
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PersonalizationFragment : Fragment() {

    private var _binding: FragmentPersonalizationBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageStyle: ImageView
    private lateinit var btnBuy: Button

    private lateinit var myGenero: LiveData<String>
    private lateinit var myProfile: LiveData<Int>
    private lateinit var myHairColor: LiveData<String>
    private lateinit var myHair: LiveData<Int>
    private lateinit var myPrice: LiveData<Int>
    private lateinit var myType: LiveData<String>
    private lateinit var myClothesColor: LiveData<String>
    private lateinit var myDesing: LiveData<Int>
    private lateinit var url: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalizationBinding.inflate(inflater,container, false)
        imageStyle = binding.imgDesingEnd
        btnBuy = binding.btnBuy

        btnBuy.setOnClickListener {
            val buyFragment = BuyFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, buyFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        initModel()
        initView()
        return binding.root
    }

    private fun initModel() {
        myGenero = (requireActivity() as MainActivity).productViewModel.getGenero()
        myProfile = (requireActivity() as MainActivity).productViewModel.getPerfil()
        myHairColor = (requireActivity() as MainActivity).productViewModel.getHairColor()
        myHair = (requireActivity() as MainActivity).productViewModel.getHair()
        myPrice = (requireActivity() as MainActivity).productViewModel.getPrice()
        myType = (requireActivity() as MainActivity).productViewModel.getType()
        myClothesColor = (requireActivity() as MainActivity).productViewModel.getColor()
        myDesing = (requireActivity() as MainActivity).productViewModel.getDesign()
        url = "${myGenero.value}_PERFIL${myProfile.value}_HAIR${myHair.value}_${myHairColor.value}_${UtlisDesing.getTypeUppercase(myType.value)}_${UtlisDesing.getColorUppercase(myClothesColor.value)}_ANIMADO${myDesing.value}"

        (requireActivity() as MainActivity).productViewModel.setProductAndPerfil(url)
        Log.i("CARO", "la constante es: ${url}")
        Log.i("CARO", "El modelo trae: " +
                "[myType =  ${myType.value}][myClothesColor = ${myClothesColor.value}]" +
                "[myDesing = ${myDesing.value}][myHair = ${myHair.value}]")
    }

    private fun initView() {
        val urlFile = Urls::class.java.getField(url).get(null) as String
        Glide.with(requireContext()).load(urlFile)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageStyle)
    }


}