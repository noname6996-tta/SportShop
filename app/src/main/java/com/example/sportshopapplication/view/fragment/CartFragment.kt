package com.example.sportshopapplication.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportshopapplication.adapter.ItemCartAdapter
import com.example.sportshopapplication.databinding.FragmentCartBinding
import com.example.sportshopapplication.viewmodel.CartViewModel
import com.proxglobal.worlcupapp.base.BaseFragment

class CartFragment: BaseFragment<FragmentCartBinding>() {
    var cartItemAdapter = ItemCartAdapter()
    override fun getDataBinding(): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater)
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
        binding.viewThanhToan.setOnClickListener {
            var action = CartFragmentDirections.actionCartFragmentToBuyFragment()
            findNavController().navigate(action)
        }
    }
}