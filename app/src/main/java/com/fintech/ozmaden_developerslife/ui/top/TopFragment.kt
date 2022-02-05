package com.fintech.ozmaden_developerslife.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.databinding.FragmentPostBinding
import com.fintech.ozmaden_developerslife.model.Post
import com.fintech.ozmaden_developerslife.ui.PostFragment
import com.fintech.ozmaden_developerslife.ui.random.RandomPostViewModel
import com.fintech.ozmaden_developerslife.utils.GifLoader

class TopFragment : PostFragment() {
    override fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this).get(TopPostViewModel::class.java)
    }
}