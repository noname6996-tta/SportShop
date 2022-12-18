package com.example.sportshopapplication.view.fragment

import com.example.sportshopapplication.databinding.FragmentCategoryBinding
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.proxglobal.worlcupapp.base.BaseFragment

class CategoryFragment: BaseFragment<FragmentCategoryBinding>() {
    override fun getDataBinding(): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater)
    }
}