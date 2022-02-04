package com.fintech.ozmaden_developerslife.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fintech.ozmaden_developerslife.databinding.FragmentRandomBinding

class RandomFragment : Fragment() {

    private lateinit var viewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(RandomViewModel::class.java)

        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRandom

        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val gifView: ImageView = binding.gif

        viewModel.post.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.gifURL.replace("http", "https")).into(gifView);
            textView.text = it.description
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}