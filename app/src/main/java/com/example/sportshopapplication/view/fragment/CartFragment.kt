package com.example.sportshopapplication.view.fragment

import com.example.sportshopapplication.databinding.FragmentCartBinding
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.proxglobal.worlcupapp.base.BaseFragment

class CartFragment: BaseFragment<FragmentCartBinding>() {
    override fun getDataBinding(): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater)
    }
}