package com.application.apptienda.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentProductBinding
import com.application.apptienda.ui.color.ColorFragment
import com.application.apptienda.utils.Constants
import com.application.apptienda.utils.Urls
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        Glide.with(requireContext()).load(Urls.SHIRT_BLACK)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.btnShirt)

        Glide.with(requireContext()).load(Urls.SWEATER_BLACK)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.btnSweater)

        val btnShirt: ImageButton = binding.btnShirt

        btnShirt.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setType("shirt")
            (requireActivity() as MainActivity).productViewModel.setPrice(Constants.PRICE_SHIRT)
            val colorFragment = ColorFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, colorFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        val btnSweater: ImageButton = binding.btnSweater

        btnSweater.setOnClickListener {
            (requireActivity() as MainActivity).productViewModel.setType("sweater")
            (requireActivity() as MainActivity).productViewModel.setPrice(Constants.PRICE_SWEATER)
            val colorFragment = ColorFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, colorFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return binding.root
    }

}