package com.example.sportshopapplication.view.fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportshopapplication.databinding.FragmentCartBinding
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.example.sportshopapplication.viewmodel.CartViewModel
import com.example.sportshopapplication.viewmodel.FavoriteItemViewModel
import com.proxglobal.worlcupapp.base.BaseFragment

class CartFragment: BaseFragment<FragmentCartBinding>() {
    var cartItemAdapter = ItemCartAdapter()
    override fun getDataBinding(): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater)
        //ABDfgdfgfdCC
        //manh cho

    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        val musicPlayListViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        binding.recCart.adapter = cartItemAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recCart.layoutManager = linearLayoutManager
        musicPlayListViewModel.readAllData.observe(viewLifecycleOwner, Observer {playlist ->
            cartItemAdapter.setItemList(playlist,requireContext())
        })
    }

    override fun addEvent() {
        super.addEvent()
        cartItemAdapter.setClickShowMusic {
            val musicPlayListViewModel = ViewModelProvider(this)[CartViewModel::class.java]
            musicPlayListViewModel.deleteCart(it)
        }
    }
}