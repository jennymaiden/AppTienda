package com.application.apptienda.ui.desing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentDesingBinding
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.common.utils.Constants
import com.application.apptienda.common.utils.UtlisDesing

class DesingFragment : Fragment(), onClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var desingList: ArrayList<Desing>
    private lateinit var desingAdapter: DesingAdapter


    private var _binding: FragmentDesingBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageclothes: ImageView
    private lateinit var myType: LiveData<String>
    private lateinit var myColor: LiveData<String>
    private lateinit var myPrice: LiveData<Int>
    private var PRICE_BASE: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDesingBinding.inflate(inflater, container, false)

        val txtPrice: TextView = binding.txtPrice
        imageclothes = binding.imgAnimation
        myType = (requireActivity() as MainActivity).productViewModel.getType()
        myColor = (requireActivity() as MainActivity).productViewModel.getColor()
        imageclothes.setImageResource(UtlisDesing.getChageColor(myColor.value, myType.value))
        myPrice =(requireActivity() as MainActivity).productViewModel.getPrice()
        PRICE_BASE = myPrice.value ?: 0
        (requireActivity() as MainActivity).productViewModel.getPrice().observe(viewLifecycleOwner) { price ->
            txtPrice.text = "$ ${price}"
        }
        initRecyclerView(binding)
        initUI()

        return binding.root
    }

    private fun initRecyclerView(binding: FragmentDesingBinding) {
        recyclerView = binding.recyclerDesing
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        desingList = ArrayList()
        addDataToList()
        desingAdapter = DesingAdapter(desingList, this)
        recyclerView.adapter = desingAdapter

    }

    private fun initUI() {
        val btnBack: Button = binding.btnBack
        val btnBuy: Button = binding.btnBuy

        btnBuy.setOnClickListener{
            val bundle = Bundle().apply {
                putString("key", "${myType.value}_${myColor.value}_animado_${(requireActivity() as MainActivity).productViewModel.getDesign().value}")
            }
            val dialogView = DialogDesing()
            dialogView.arguments = bundle
            dialogView.show(parentFragmentManager, "MyDialog")
        }

        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun addDataToList() {
        desingList.add(Desing(R.mipmap.animado_1, "Super Hero"))
        desingList.add(Desing(R.mipmap.animado_2, "Corazón"))
        desingList.add(Desing(R.mipmap.animado_3, "Sapito"))
    }

    override fun onClick(desing: Desing, position: Int) {
        //Toast.makeText(requireContext(), "Hola el nombre es ${desing.desingName}", Toast.LENGTH_SHORT).show()
        setProductViewModelDesing(position+1)
        imageclothes.setImageResource(UtlisDesing.getDesing(position+1, myType.value, myColor.value, requireContext()) )
    }

    private fun setProductViewModelDesing(logo: Int) {
        when (logo) {
            1 -> {(requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE + Constants.PRICE_FIGURE_1)}
            2 -> {(requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE + Constants.PRICE_FIGURE_2)}
            3 -> {(requireActivity() as MainActivity).productViewModel.setPrice(PRICE_BASE + Constants.PRICE_FIGURE_3)}
        }
        (requireActivity() as MainActivity).productViewModel.setDesign(logo)
    }
}