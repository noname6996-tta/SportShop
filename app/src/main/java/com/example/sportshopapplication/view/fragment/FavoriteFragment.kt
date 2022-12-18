package com.example.sportshopapplication.view.fragment

import com.example.sportshopapplication.databinding.FragmentFavoriteBinding
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.proxglobal.worlcupapp.base.BaseFragment

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {
    override fun getDataBinding(): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }
}