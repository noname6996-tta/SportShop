package com.example.sportshopapplication.view.fragment

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sportshopapplication.R
import com.example.sportshopapplication.databinding.FragmentItemBinding
import com.example.sportshopapplication.model.Item
import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.model.local.FavoriteItem
import com.example.sportshopapplication.viewmodel.CartViewModel
import com.example.sportshopapplication.viewmodel.FavoriteItemViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.proxglobal.worlcupapp.base.BaseFragment

class ItemFragment : BaseFragment<FragmentItemBinding>() {
    private var isFavorite: Boolean = false
    private lateinit var item: Item
    private val args: ItemFragmentArgs by navArgs()
    override fun getDataBinding(): FragmentItemBinding {
        return FragmentItemBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        item = args.item
        Glide.with(requireContext()).load(item.anh).error(R.drawable.img)
            .into(binding.imgItem)
        binding.tvGiaSanPham.setText(item.gia)
        binding.tvNameItem.setText(item.tenSanPham)
        binding.tvSexItem.setText(item.gioiTinh)
        binding.tvDescItem.setText(item.moTa)
        checkFavorite(item)
    }

    override fun addEvent() {
        super.addEvent()
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAddToCart.setOnClickListener {
            showBottomSheetAdd(item)
        }
        binding.btnBuyNow.setOnClickListener {

        }
        binding.imgFavorite.setOnClickListener {
            val musicPlayListViewModel = ViewModelProvider(this)[FavoriteItemViewModel::class.java]
            val musicPlaylist = FavoriteItem(
                0,
                item.maSanPham,
                item.tenSanPham,
                item.anh,
                item.gia
            )
            if (!isFavorite) {
                musicPlayListViewModel.addCart(musicPlaylist)
                Toast.makeText(
                    requireContext(),
                    "Add" + item.tenSanPham + " to favorite success",
                    Toast.LENGTH_SHORT
                ).show()
                binding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_true_24)
            } else {
                musicPlayListViewModel.deleteCart(item.maSanPham)
                Toast.makeText(
                    requireContext(),
                    "Remove" + item.tenSanPham + " to favorite success",
                    Toast.LENGTH_SHORT
                ).show()
                binding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    private fun showBottomSheetAdd(item: Item) {
        val bottomSheetDialogSong = BottomSheetDialog(this.requireContext());
        bottomSheetDialogSong.setContentView(R.layout.bottom_sheet_add_to_cart);
        bottomSheetDialogSong.show()

        val imgItem = bottomSheetDialogSong.findViewById<ImageView>(R.id.imgItem)
        Glide.with(requireContext()).load(item.anh).error(R.drawable.img)
            .into(imgItem!!)
        val nameItem = bottomSheetDialogSong.findViewById<TextView>(R.id.tvItemName)
        nameItem?.setText(item.tenSanPham)
        val btnPlus = bottomSheetDialogSong.findViewById<ImageView>(R.id.btnPlusItem)
        val btnRemove = bottomSheetDialogSong.findViewById<ImageView>(R.id.imgRemoveItem)
        var number = bottomSheetDialogSong.findViewById<TextView>(R.id.tvNumberItem)
        var count  = number?.text.toString().toInt()
        btnPlus?.setOnClickListener {
            count ++
            number?.setText(count.toString())
        }
        btnRemove?.setOnClickListener {
            count --
            number?.setText(count.toString())
        }

        val btnAddToCart = bottomSheetDialogSong.findViewById<View>(R.id.btnAddNow)
        btnAddToCart?.setOnClickListener {
            val musicPlayListViewModel = ViewModelProvider(this)[CartViewModel::class.java]
            var cart = Cart(0,item.maSanPham,0,item.tenSanPham,number?.text.toString().toInt(),"54.000đ",item.anh)
            musicPlayListViewModel.addCart(cart)
            Toast.makeText(
                requireContext(),
                "Thêm sản phẩm vào giỏ thành công",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkFavorite(itemCheck: Item) {
        val musicPlayListViewModel = ViewModelProvider(this)[FavoriteItemViewModel::class.java]
        musicPlayListViewModel.readAllData.observe(this,
            Observer { musicplaylist ->
                for (item: Int in 0..musicplaylist.size - 1) {
                    if (musicplaylist[item].maSanPham.equals(itemCheck.maSanPham)) {
                        binding.imgFavorite!!.setImageResource(R.drawable.ic_baseline_favorite_true_24)
                        isFavorite = true
                    } else {
                        binding.imgFavorite!!.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        isFavorite = false
                    }

                }
            })
    }
}