package com.fintech.ozmaden_developerslife.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fintech.ozmaden_developerslife.GifLoader
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
                binding.author.text = "Автор: " + it.author
                binding.textDate.text = it.date

                renderPostGif(it)
                updatePreviousBtn()
            }
        )
    }

    private fun setPostPreview(post: Post) {
//        binding.apply { gif.load(post.previewURL) { scale(Scale.FILL) } }
//        ImageLoader().loadImage(binding.gif, post.gifURL)
    }

    private fun renderPostGif(post: Post) {
        binding.apply {
            //            binding.card.visibility = View.GONE
            //            binding.shimmerViewContainer.visibility = View.VISIBLE
            //            binding.shimmerViewContainer.startShimmer()

//            gif.load(post.gifURL) {
//                crossfade(true)
//                crossfade(150)
//                scale(Scale.FILL)
//            }

            GifLoader().loadImage(gif, post)

            //            binding.shimmerViewContainer.visibility = View.VISIBLE

            //            binding.shimmerViewContainer.stopShimmer()
            //            binding.shimmerViewContainer.visibility = View.GONE
            //            binding.card.visibility = View.VISIBLE
            //            preview.visibility = View.GONE

            //            shimmerViewContainer.stopShimmer()
            //            shimmerViewContainer.clearAnimation()
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
