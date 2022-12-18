package com.example.sportshopapplication.view.fragment

import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.example.sportshopapplication.databinding.FragmentSettingBinding
import com.proxglobal.worlcupapp.base.BaseFragment

class SettingFragment: BaseFragment<FragmentSettingBinding>() {
    override fun getDataBinding(): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }
}