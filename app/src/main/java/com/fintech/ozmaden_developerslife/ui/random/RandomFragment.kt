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
import com.fintech.ozmaden_developerslife.model.Post

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

        binding.prevBtn.setOnClickListener {
            nextPost()
        }

        binding.prevBtn.setOnClickListener {
            previousPost()
        }

        val textView: TextView = binding.text

        viewModel.description.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val gifView: ImageView = binding.gif

        viewModel.post.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.gifURL.replace("http", "https")).into(gifView);
        })

        viewModel.post.observe(viewLifecycleOwner, Observer {
            updatePost(it)
            if (viewModel.position > 0) {
                binding.prevBtn.isEnabled = true
                binding.prevBtn.show()
            } else {
                binding.prevBtn.isEnabled = false
                binding.prevBtn.hide()
            }
        })

        return root
    }

    private fun nextPost() {
        hidePost()
        viewModel.onNext()
    }

    private fun previousPost() {
        viewModel.onPrevious()
    }

    private fun hidePost() {
        binding.text.visibility = View.GONE
    }

    private fun updatePost(newPost: Post) {
        binding.text.text = newPost.description
        binding.text.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}