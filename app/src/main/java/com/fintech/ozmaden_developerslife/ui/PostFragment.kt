package com.fintech.ozmaden_developerslife.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.fintech.ozmaden_developerslife.databinding.FragmentPostBinding
import com.fintech.ozmaden_developerslife.utils.GifLoader

internal abstract class PostFragment : Fragment() {

    protected lateinit var viewModel: PostViewModel

    private var _binding: FragmentPostBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private fun started() = viewModel.position > 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        viewModel = setUpViewModel()

        setObserver()
        setBtnClickListeners()
        startFeed()

        return binding.root
    }

    protected abstract fun setUpViewModel(): PostViewModel

    private fun startFeed() {
        if (!started()) {
            nextPost()
        }
    }

    private fun setObserver() {
        val textView: TextView = binding.text
        val gifView: ImageView = binding.gif

        viewModel.post.observe(viewLifecycleOwner, Observer {
            GifLoader.loadImage(it.gifURL, gifView)
            textView.text = it.description
            updatePreviousBtn()
        })
    }

    private fun setBtnClickListeners() {
        binding.apply {
            nextBtn.setOnClickListener {
                nextPost()
            }
            prevBtn.setOnClickListener {
                previousPost()
            }
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
            if (started()) {
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