package com.example.sportshopapplication.view.fragment

import androidx.navigation.fragment.findNavController
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.proxglobal.worlcupapp.base.BaseFragment
import com.example.sportshopapplication.R

class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override fun getDataBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()

    }

    override fun addEvent() {
        super.addEvent()
        binding.searchView.setOnSearchClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}