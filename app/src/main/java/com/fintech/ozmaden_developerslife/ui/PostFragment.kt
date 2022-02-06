package com.fintech.ozmaden_developerslife.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fintech.ozmaden_developerslife.GifLoader
import com.fintech.ozmaden_developerslife.R.string as strings
import com.fintech.ozmaden_developerslife.databinding.FragmentPostBinding
import com.fintech.ozmaden_developerslife.databinding.ItemErrorBinding
import com.fintech.ozmaden_developerslife.ext.isVisible

/** Главный фрагмент, где происходит основная работа с постами. */
internal abstract class PostFragment : Fragment() {

    private lateinit var viewModel: PostViewModel
    private lateinit var binding: FragmentPostBinding
    private lateinit var errorBinding: ItemErrorBinding

    protected abstract fun viewModel(): PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostBinding.inflate(inflater, container, false)
        errorBinding = ItemErrorBinding.bind(binding.root)

        viewModel = viewModel()

        setPostObserver()
        setFailObserver()
        initClickListeners()

        if (viewModel.position < 0) {
            viewModel.loadNextPost()
        }

        return binding.root
    }

    private fun setPostObserver() {
        viewModel.post.observe(viewLifecycleOwner) { post ->
            binding.postTitle.text = post.description
            binding.author.text = getString(strings.author, post.author)
            binding.textDate.text = post.date

            GifLoader.loadImage(binding.gif, post)
            renderPreviousButton()
        }
    }

    private fun setFailObserver() {
        viewModel.onLoadFail.observe(viewLifecycleOwner) { isFail ->
            binding.next.isEnabled = isFail != true
            binding.previous.isEnabled = isFail != true
            binding.card.isVisible = isFail != true
            errorBinding.errorGroup.isVisible = isFail == true
        }
    }

    private fun initClickListeners() {
        binding.next.setOnClickListener { viewModel.loadNextPost() }
        binding.previous.setOnClickListener { viewModel.loadPrevPost() }
        errorBinding.errorRetry.setOnClickListener { viewModel.loadNextPost() }
    }

    private fun renderPreviousButton() =
        (viewModel.position > 0).let { predicate ->
            binding.previous.isEnabled = predicate

            if (predicate) {
                binding.previous.show()
            } else {
                binding.previous.hide()
            }
        }
}
