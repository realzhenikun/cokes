package com.example.aninterface

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.aninterface.databinding.PostViewpagerBinding

class PostViewpagerAdapter : RecyclerView.Adapter<PostViewpagerAdapter.ViewHolder>() {

    private var data = mutableListOf<Post>()

    fun setData(data: MutableList<Post>) {
        this.data = data
        //刷新
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewpagerAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: PostViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Post) {
                binding.upName.text = binding.root.context.getString(R.string.up_post, data.up.name)
                binding.introduction.text = data.introduction
                binding.postImage.setImageResource(data.image)
            }
    }
}