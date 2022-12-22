package com.example.sportshopapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.ItemCategoryBinding
import com.example.sportshopapplication.databinding.LayoutHomeImageBinding
import com.example.sportshopapplication.model.Category
import com.example.sportshopapplication.model.local.Photos


class CategoryAdapter : RecyclerView.Adapter<CategoryViewHolder>() {
    private var musics: List<Category> = listOf()
    private lateinit var context: Context
    fun setCategoryList(movies: List<Category>, context: Context) {
        this.musics = movies.toMutableList()
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val music = musics[position]
        Glide.with(holder.itemView.context).load(music.anh).error(R.drawable.img)
            .into(holder.binding.imgCategory)
        holder.binding.tvNameCategory.text = music.tenDanhMuc
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

class CategoryViewHolder(val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
}