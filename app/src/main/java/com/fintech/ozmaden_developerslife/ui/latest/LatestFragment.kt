package com.fintech.ozmaden_developerslife.ui.latest

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
import com.fintech.ozmaden_developerslife.databinding.FragmentLatestBinding
import com.fintech.ozmaden_developerslife.model.Post

class LatestFragment : Fragment() {

    private lateinit var viewModel: LatestPostViewModel
    private var _binding: FragmentLatestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel =
            ViewModelProvider(this).get(LatestPostViewModel::class.java)

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

        binding.nextBtn.setOnClickListener {
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

        return root
    }

    private fun nextPost() {
        hidePost()
        viewModel.nextPost()
    }

    private fun previousPost() {
        viewModel.previousPost()
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