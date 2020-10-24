package com.example.apiblog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


class MainFragment : Fragment(R.layout.fragment_main), CellClickListener, CoroutineScope by MainScope(){
    private val viewModel: MainViewModel by viewModels()

    private lateinit var recycler: RecyclerView
    private lateinit var viewAdapter: BlogAdapter
    private var arrayBlog = mutableListOf<Posts>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = BlogAdapter(arrayBlog, this)
        viewModel.blogItems.observe(viewLifecycleOwner, Observer {
            viewAdapter.setItems(it ?: emptyList())
        })
        recycler = recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }
        viewModel.getBlogData()
    }

    override fun onCellClickListener(pos: String?) {
        //это так не работает через навигацию передавать значения
        //var fragment = ContentFragment.newInstance(Posts(title = pos.toString(), content = "dfgdfgd"))
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_contentFragment)
    }


    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

}
