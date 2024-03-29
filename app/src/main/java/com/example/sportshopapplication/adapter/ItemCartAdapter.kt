package com.example.sportshopapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.ItemCartBinding
import com.example.sportshopapplication.model.local.Cart


class ItemCartAdapter : RecyclerView.Adapter<CartItemViewHolder>() {
    private var Items: List<Cart> = listOf()
    private lateinit var context: Context
    fun setItemList(movies: List<Cart>, context: Context) {
        this.Items = movies.toMutableList()
        this.context = context
        notifyDataSetChanged()
    }

    private var onClickPlayItem: ((Cart) -> Unit)? = null
    fun setClickShowMusic(listener: ((Cart) -> Unit)) {
        onClickPlayItem = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val Item = Items[position]
        holder.binding.tvItemName.text = Item.tenSanPham
        holder.binding.tvGiaSanPham.text = Item.gia
        holder.binding.tvNumberItem.text = Item.soLuong.toString()
        Glide.with(holder.itemView.context).load(Item.anh).error(R.drawable.demo_img_download)
            .into(holder.binding.imgItem)
        holder.binding.imgRemoveItem.setOnClickListener {
            onClickPlayItem?.let {
                it(Item)
            }
        }
//        holder.binding.layoutTopRanking.setOnClickListener {
//            onClickPlayItem?.let {
//                it(Item)
//            }
//        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}

class CartItemViewHolder(val binding: ItemCartBinding) :
    RecyclerView.ViewHolder(binding.root) {
}