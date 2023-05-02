package com.application.apptienda.ui.style

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentStyleBinding
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.ui.personalization.PersonalizationFragment
import com.application.apptienda.common.constans.Urls
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class StyleFragment : Fragment(), onClickStyle {

    private lateinit var recyclerView: RecyclerView
    private lateinit var styleList: ArrayList<Style>
    private lateinit var profileAdapter: StyleAdpter
    private lateinit var imageStyle: ImageView

    private var _binding: FragmentStyleBinding? = null
    private val binding get() = _binding!!

    private lateinit var myGenero: LiveData<String>
    private lateinit var myProfile: LiveData<Int>

    private var posicion: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStyleBinding.inflate(inflater, container, false)
        imageStyle = binding.imgStyle

        myGenero = (requireActivity() as MainActivity).productViewModel.getGenero()
        myProfile = (requireActivity() as MainActivity).productViewModel.getPerfil()

        initRecyclerView(binding)
        initUI()
        return binding.root
    }

    private fun initRecyclerView(binding: FragmentStyleBinding) {
        recyclerView = binding.recyclerProfile
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        styleList = ArrayList()
        addDataToListHair()
        profileAdapter = StyleAdpter(styleList, this)
        recyclerView.adapter = profileAdapter
    }

    private fun initUI() {
        val btnBack: Button = binding.btnBack
        val btnStyle: Button = binding.btnStyle
        val btnHairBlack: ImageButton = binding.btnHairBlack
        val btnHairBrown: ImageButton = binding.btnHairBrown
        val btnHairRed: ImageButton = binding.btnHairRed

        btnStyle.setOnClickListener {
            //Toast.makeText(requireContext(), "Boton de estilo", Toast.LENGTH_SHORT).show()
            val personalizationFragment = PersonalizationFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, personalizationFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnHairBlack.setOnClickListener {
            //Toast.makeText(requireContext(), "Boton de btnHairBlack", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).productViewModel.setHairColor("BLACK")
            val test = "${myGenero.value}_PERFIL${myProfile.value}_HAIR${posicion}_BLACK"
            Log.i("CARO", "la constante es: ${test}")
            val url = Urls::class.java.getField(test).get(null) as String
            Glide.with(requireContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageStyle)
        }

        btnHairBrown.setOnClickListener {
            //Toast.makeText(requireContext(), "Boton de btnHairBrown", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).productViewModel.setHairColor("BROWN")
            val test = "${myGenero.value}_PERFIL${myProfile.value}_HAIR${posicion}_BROWN"
            Log.i("CARO", "la constante es: ${test}")
            val url = Urls::class.java.getField(test).get(null) as String
            Glide.with(requireContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageStyle)
        }

        btnHairRed.setOnClickListener {
            //Toast.makeText(requireContext(), "Boton de btnHairRed", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).productViewModel.setHairColor("RED")
            val test = "${myGenero.value}_PERFIL${myProfile.value}_HAIR${posicion}_RED"
            Log.i("CARO", "la constante es: ${test}")
            val url = Urls::class.java.getField(test).get(null) as String
            Glide.with(requireContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageStyle)
        }
    }

    private fun addDataToListHair() {
        styleList.add(Style(Urls.GIRL_HAIR1, "Estilo 1"))
        styleList.add(Style(Urls.GIRL_HAIR2, "Estilo 2"))
        styleList.add(Style(Urls.GIRL_HAIR3, "Estilo 3"))
    }

    override fun onClick(style: Style, position: Int) {
        //Toast.makeText(requireContext(), "Hola el style es ${style.desingName}", Toast.LENGTH_SHORT).show()
        (requireActivity() as MainActivity).productViewModel.setHair(position+1)
        //GIRL_PERFIL1_HAIR1_BLACK
        posicion = position+1
        val test = "${myGenero.value}_PERFIL${myProfile.value}_HAIR${position+1}_BLACK"
        Log.i("CARO", "la constante es: ${test}")
        val url = Urls::class.java.getField(test).get(null) as String
        Glide.with(requireContext()).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageStyle)

    }

}