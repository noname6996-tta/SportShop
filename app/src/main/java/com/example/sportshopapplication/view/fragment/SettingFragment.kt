package com.example.sportshopapplication.view.fragment

import android.app.AlertDialog
import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.example.sportshopapplication.databinding.FragmentSettingBinding
import com.example.sportshopapplication.view.activity.LoginActivity
import com.proxglobal.worlcupapp.base.BaseFragment

class SettingFragment: BaseFragment<FragmentSettingBinding>() {
    override fun getDataBinding(): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }

    override fun addEvent() {
        super.addEvent()
        binding.viewChangeAccount.setOnClickListener {
            var action = SettingFragmentDirections.actionSettingFragmentToChangeAccountFragment()
            findNavController().navigate(action)
        }
        binding.viewLogout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){_,_ ->
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("No"){_,_ ->

            }
            builder.setTitle("Xác nhận đăng xuất?")
            builder.setMessage("Bạn chắc chắn chứ??")
            builder.create().show()
        }

        binding.viewDonHang.setOnClickListener {
            var action =SettingFragmentDirections.actionSettingFragmentToShowDonHangFragment()
            findNavController().navigate(action)
        }
    }
}