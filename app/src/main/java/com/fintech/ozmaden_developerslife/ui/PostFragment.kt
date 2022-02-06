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
import com.fintech.ozmaden_developerslife.model.Post

internal abstract class PostFragment : Fragment() {

    protected lateinit var viewModel: PostViewModel

    private var _binding: FragmentPostBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        viewModel = setUpViewModel()

        setPostObserver()
        setFailObserver()
        setBtnClickListeners()
        startFeed()

        return binding.root
    }

    protected abstract fun setUpViewModel(): PostViewModel

    private fun setPostObserver() {
        viewModel.post.observe(
            viewLifecycleOwner,
            {
                setPostPreview(it)
                binding.text.text = it.description
                loadPostGif(it)
                updatePreviousBtn()
            }
        )
    }

    private fun setPostPreview(post: Post) {
        binding.apply { preview.load(post.previewURL) { scale(Scale.FILL) } }
    }

    private fun loadPostGif(post: Post) {
        binding.apply {
            gif.load(post.gifURL) {
                //                placeholder(R.drawable.ic_baseline_loop_24)
                crossfade(true)
                crossfade(150)
                scale(Scale.FILL)
            }
        }
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

    private fun startFeed() {
        if (viewModel.position < 0) {
            nextPost()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
