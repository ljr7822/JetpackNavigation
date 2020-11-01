package com.example.iwen.jetpacknavigation.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.iwen.jetpacknavigation.R
import com.example.iwen.jetpacknavigation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * 登录的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class LoginFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        rootView.toRegisterPage.setOnClickListener {
            // 跳转到注册界面
            findNavController().navigate(R.id.to_register_fragment)
        }

        rootView.toForgetPage.setOnClickListener {
            // 跳转到找回密码界面
            findNavController().navigate(R.id.to_forget_fragment)
        }

        rootView.toAgreementPage.setOnClickListener {
            // 跳转到找回密码界面
            findNavController().navigate(R.id.to_agreement_page)
        }

    }
}