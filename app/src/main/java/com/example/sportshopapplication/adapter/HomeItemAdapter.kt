package com.example.ItemRanking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.LayoutItemBinding
import com.example.sportshopapplication.model.Item


class HomeItemAdapter : RecyclerView.Adapter<HomeFragmentRankingViewHolder>() {
    private var Items: List<Item> = listOf()
    private lateinit var context: Context
    fun setItemList(movies: List<Item>, context: Context) {
        this.Items = movies.toMutableList()
        this.context = context
        notifyDataSetChanged()
    }

    private var onClickPlayItem: ((Item) -> Unit)? = null
    fun setClickShowMusic(listener: ((Item) -> Unit)) {
        onClickPlayItem = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentRankingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return HomeFragmentRankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentRankingViewHolder, position: Int) {
        val Item = Items[position]
        holder.binding.tvTopRankingNameSong.text = Item.tenSanPham
        holder.binding.tvTopRankingArtisSong.text = Item.gia
        Glide.with(holder.itemView.context).load(Item.anh).error(R.drawable.demo_img_download)
            .into(holder.binding.imgTopRanking)
        holder.binding.layoutTopRanking.setOnClickListener {
            onClickPlayItem?.let {
                it(Item)
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}

class HomeFragmentRankingViewHolder(val binding: LayoutItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}