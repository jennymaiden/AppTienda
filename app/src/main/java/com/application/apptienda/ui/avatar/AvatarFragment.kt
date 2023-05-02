package com.application.apptienda.ui.avatar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.application.apptienda.mainModule.MainActivity
import com.application.apptienda.R
import com.application.apptienda.databinding.FragmentAvatarBinding
import com.application.apptienda.ui.profile.ProfileFragment
import com.application.apptienda.common.utils.Constants

class AvatarFragment : Fragment() {

    private var _binding: FragmentAvatarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAvatarBinding.inflate(inflater, container, false)

        val btnGirl: ImageButton = binding.btnGirl

        btnGirl.setOnClickListener{
            (requireActivity() as MainActivity).productViewModel.setGenero(Constants.GIRL)
            val profileFragment = ProfileFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, profileFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}