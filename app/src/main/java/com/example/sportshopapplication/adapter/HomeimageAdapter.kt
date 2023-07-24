package com.example.sportshopapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.LayoutHomeImageBinding
import com.example.sportshopapplication.model.local.Photos


class HomeDownloadAdapter : RecyclerView.Adapter<HomeFragmentDownloadViewHolder>() {
    private var musics: List<Photos> = listOf()
    private lateinit var context: Context
    fun setPhotosList(movies: List<Photos>, context: Context) {
        this.musics = movies.toMutableList()
        this.context = context
        notifyDataSetChanged()
    }

    private var onClickPlayMusic: ((i: Int) -> Unit)? = null
    fun setClickPlayMusic(position: ((i: Int) -> Unit)) {
        onClickPlayMusic = position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentDownloadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutHomeImageBinding.inflate(inflater, parent, false)
        return HomeFragmentDownloadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentDownloadViewHolder, position: Int) {
        val music = musics[position]
        Glide.with(holder.itemView.context).load(music.image).error(R.drawable.img)
            .into(holder.binding.imgHomeSale)
        holder.binding.imgHomeSale.setOnClickListener {
            onClickPlayMusic?.let {
                it(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

class HomeFragmentDownloadViewHolder(val binding: LayoutHomeImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
}