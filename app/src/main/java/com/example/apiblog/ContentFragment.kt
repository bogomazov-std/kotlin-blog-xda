package com.example.apiblog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_content.*

private const val ARG_PARAM1 = "title"

class ContentFragment : Fragment(R.layout.fragment_content) {

    private var param1: Posts? = null
    val arg = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_title.text = param1?.title
    }

    companion object {
        //с этим лучше не работать...
        @JvmStatic
        fun newInstance(post: Posts?) =
            ContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, post)
                }
            }
    }
}