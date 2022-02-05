package com.fintech.ozmaden_developerslife.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech.ozmaden_developerslife.R
import com.fintech.ozmaden_developerslife.databinding.FragmentErrorBinding

class ErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentErrorBinding.inflate(inflater, container, false)

        binding.tryAgainButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_errorFragment_to_topFragment)
        }

        return binding.root
    }
}
