package com.example.apiblog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(
    private val list: MutableList<Posts>,
    private val cellClickListener: CellClickListener,
) : RecyclerView.Adapter<BlogAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val movie: Posts = list[position]
        holder.bind(movie, cellClickListener)
    }

    override fun getItemCount(): Int = list.size

    fun setItems(items: List<Posts>) {
        list.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class PostViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private val blogTitleView: TextView = itemView.findViewById(R.id.list_title)
        private val blogContentView: TextView = itemView.findViewById(R.id.list_description)
        private val clickHandler = itemView.findViewById<View>(R.id.lisen_click_layout)

        fun bind(movie: Posts, onClick: CellClickListener) {
            blogTitleView.text = movie.title
            blogContentView.text = movie.content
            clickHandler.setOnClickListener { onClick.onCellClickListener(movie.title) }
        }
    }
}
