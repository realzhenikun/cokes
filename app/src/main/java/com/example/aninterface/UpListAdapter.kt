package com.example.aninterface

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aninterface.databinding.UpListBinding

class UpListAdapter : RecyclerView.Adapter<UpListAdapter.ViewHolder>() {
    private var data = mutableListOf<Up>()
    private var itemClickListener: OnItemClickListener? = null
    private var itemLongClickListener: OnItemLongClickListener? = null

    /*
        引入接口,设置监听器
        分别设置一个点击监听器和一个长按监听器
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(up: Up): Boolean
    }

    fun setData(data: MutableList<Up>) {
        this.data = data
        //刷新
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        itemLongClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UpListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upList = data[position]
        holder.bind(upList)
        holder.itemView.setOnClickListener { itemClickListener?.onItemClick(position) }
        holder.itemView.setOnLongClickListener { itemLongClickListener?.onItemLongClick(upList) == true }
    }

    inner class ViewHolder(private val binding: UpListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upList: Up) {
            binding.upName.text = upList.name
            binding.upImage.setImageResource(upList.image)
        }
    }
}