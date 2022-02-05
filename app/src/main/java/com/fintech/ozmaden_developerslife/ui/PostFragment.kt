package com.fintech.ozmaden_developerslife.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Scale
import com.fintech.ozmaden_developerslife.R
import com.fintech.ozmaden_developerslife.databinding.FragmentPostBinding

internal abstract class PostFragment : Fragment() {

    protected lateinit var viewModel: PostViewModel

    private var _binding: FragmentPostBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        viewModel = setUpViewModel()

        setObserver()
        setFailObserver()
        setBtnClickListeners()
        startFeed()

        return binding.root
    }

    protected abstract fun setUpViewModel(): PostViewModel

    private fun startFeed() {
        if (viewModel.position < 0) {
            nextPost()
        }
    }

    private fun setObserver() {
        viewModel.post.observe(
            viewLifecycleOwner,
            {
                binding.apply { gif.load(it.previewURL) }
                binding.apply {
                    gif.load(it.gifURL) {
                        //                        if (it.gifSize > 5242880 ){
                        //                            placeholder(R.drawable.ic_baseline_loop_24)
                        //                        }
                        crossfade(true)
                        crossfade(300)
                        scale(Scale.FILL)
                    }
                    text.text = it.description
                }
                updatePreviousBtn()
            }
        )
    }

    private fun setFailObserver() {
        viewModel.onLoadFail.observe(
            viewLifecycleOwner,
            {
                if (it == true) {
                    this.view?.let {
                        findNavController().navigate(R.id.action_postFragment_to_errorFragment)
                    }
                }
            }
        )
    }

    private fun setBtnClickListeners() {
        binding.apply {
            nextBtn.setOnClickListener { nextPost() }
            prevBtn.setOnClickListener { previousPost() }
        }
    }

    private fun nextPost() {
        viewModel.nextPost()
    }

    private fun previousPost() {
        viewModel.previousPost()
    }

    private fun updatePreviousBtn() {
        binding.apply {
            if (viewModel.position > 0) {
                prevBtn.isEnabled = true
                prevBtn.show()
            } else {
                prevBtn.isEnabled = false
                prevBtn.hide()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
