package com.example.sportshopapplication.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sportshopapplication.adapter.HomeItemFavoriteAdapter
import com.example.sportshopapplication.databinding.FragmentFavoriteBinding
import com.example.sportshopapplication.viewmodel.FavoriteItemViewModel
import com.proxglobal.worlcupapp.base.BaseFragment

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {
    var homeItemAdapter = HomeItemFavoriteAdapter()
    override fun getDataBinding(): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        val musicPlayListViewModel = ViewModelProvider(this)[FavoriteItemViewModel::class.java]
        binding.recFavorite.adapter = homeItemAdapter
        binding.recFavorite.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        musicPlayListViewModel.readAllData.observe(viewLifecycleOwner, Observer {playlist ->
            homeItemAdapter.setItemList(playlist,requireContext())
        })
        homeItemAdapter.setClickShowMusic {
            var action = HomeFragmentDirections.actionHomeFragmentToItemFragment(it)
            findNavController().navigate(action)
        }
    }
}