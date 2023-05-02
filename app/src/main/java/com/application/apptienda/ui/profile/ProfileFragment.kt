package com.application.apptienda.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentProfileBinding
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.ui.style.Style
import com.application.apptienda.ui.style.StyleFragment
import com.application.apptienda.ui.style.onClickStyle
import com.application.apptienda.common.constans.Urls
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProfileFragment : Fragment(), onClickStyle {

    private lateinit var recyclerView: RecyclerView
    private lateinit var styleList: ArrayList<Style>
    private lateinit var profileAdapter: ProfileAdapter

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageprofile: ImageView
    private lateinit var myGenero: LiveData<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        imageprofile = binding.imgProfile
        myGenero = (requireActivity() as MainActivity).productViewModel.getGenero()

        initRecyclerView(binding)
        initUI()
        return binding.root
    }

    private fun initRecyclerView(binding: FragmentProfileBinding) {
        recyclerView = binding.recyclerProfile
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        styleList = ArrayList()
        addDataToListGirl()
        profileAdapter = ProfileAdapter(styleList, this)
        recyclerView.adapter = profileAdapter
    }

    private fun initUI() {
        val btnBack: Button = binding.btnBack
        val btnNext: Button = binding.btnNest

        btnNext.setOnClickListener {
            val styleFragment = StyleFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, styleFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }
    private fun addDataToListGirl() {
        styleList.add(Style(Urls.GIRL_PERFIL1, "Perfil 1"))
        styleList.add(Style(Urls.GIRL_PERFIL2, "Perfil 2"))
        styleList.add(Style(Urls.GIRL_PERFIL3, "Perfil 3"))
    }

    override fun onClick(style: Style, position: Int) {
        //Toast.makeText(requireContext(), "Hola el nombre es ${style.desingName}", Toast.LENGTH_SHORT).show()
        (requireActivity() as MainActivity).productViewModel.setPerfil(position)
        val url = Urls::class.java.getField("GIRL_PERFIL${position}").get(null) as String
        Glide.with(requireContext()).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageprofile)


    }

}