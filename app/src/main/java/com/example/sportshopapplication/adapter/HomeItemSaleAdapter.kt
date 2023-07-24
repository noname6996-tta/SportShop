package com.example.sportshopapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.ItemSaleBinding
import com.example.sportshopapplication.model.Item


class HomeItemSaleAdapter : RecyclerView.Adapter<HomeFragmentRankingViewHolder>() {
    private var musics: List<Item> = listOf()
    private lateinit var context: Context
    fun setItemSaleList(movies: List<Item>, context: Context) {
        this.musics = movies.toMutableList()
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
        val binding = ItemSaleBinding.inflate(inflater, parent, false)
        return HomeFragmentRankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentRankingViewHolder, position: Int) {
        val music = musics[position]
        holder.binding.tvTopRankingNameSong.text = music.tenSanPham
        holder.binding.tvTopRankingArtisSong.text = music.gia
        holder.binding.tvNumberRanking.text = (position+1).toString()
        when(position){
            0-> Glide.with(holder.itemView.context).load(R.drawable.topranking1).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            1-> Glide.with(holder.itemView.context).load(R.drawable.topranking2).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            2-> Glide.with(holder.itemView.context).load(R.drawable.topranking3).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            3-> Glide.with(holder.itemView.context).load(R.drawable.topranking4).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            4-> Glide.with(holder.itemView.context).load(R.drawable.topranking4).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            5-> Glide.with(holder.itemView.context).load(R.drawable.topranking4).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
            6-> Glide.with(holder.itemView.context).load(R.drawable.topranking4).error(R.drawable.topranking1)
                .into(holder.binding.imgTagRanking)
        }
        Glide.with(holder.itemView.context).load(music.anh).error(R.drawable.demo_img_download)
            .into(holder.binding.imgTopRanking)
        holder.binding.layoutTopRanking.setOnClickListener{
            onClickPlayItem?.let{
                it(music)
            }
        }
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

class HomeFragmentRankingViewHolder(val binding: ItemSaleBinding) :
    RecyclerView.ViewHolder(binding.root) {
}