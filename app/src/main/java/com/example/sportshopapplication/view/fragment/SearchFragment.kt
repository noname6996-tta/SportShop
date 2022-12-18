package com.example.sportshopapplication.view.fragment

import androidx.navigation.fragment.findNavController
import com.example.sportshopapplication.databinding.FragmentSearchBinding
import com.proxglobal.worlcupapp.base.BaseFragment

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override fun getDataBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
    }

    override fun addEvent() {
        super.addEvent()
        binding.tvCancelSearch.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}