package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.ItemDisplayDataBinding
import com.example.retrofitapp.model.ArticelInfoData

class DisplayInfoAdapter(
    private var listInfo: ArrayList<ArticelInfoData>
) : RecyclerView.Adapter<DisplayInfoAdapter.InfoViewHolder>() {
    private lateinit var bindingDisplayInfo: ItemDisplayDataBinding

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView
        val author: AppCompatTextView
        val description: AppCompatTextView
        val articleImage: AppCompatImageView


        init {
            title = itemView.findViewById(R.id.tv_text_title)
            author = itemView.findViewById(R.id.tv_text_author)
            description = itemView.findViewById(R.id.tv_text_description)
            articleImage = itemView.findViewById(R.id.iv_articleImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_display_data, parent, false)
        bindingDisplayInfo = ItemDisplayDataBinding.bind(itemView)
        return InfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val infoToList = listInfo[position]
        holder.apply {
            title.text = infoToList.title
            author.text = infoToList.author
            description.text = infoToList.description
            Glide.with(itemView).load(infoToList.urlToImage).into(articleImage)
        }
    }

    fun deleteItem(pos: Int) {
        listInfo.removeAt(pos)
        notifyItemRemoved(pos)
    }

    override fun getItemCount() = listInfo.size

    fun setList(listInfo: ArrayList<ArticelInfoData>) {
        this.listInfo = listInfo
        notifyDataSetChanged()
    }

}