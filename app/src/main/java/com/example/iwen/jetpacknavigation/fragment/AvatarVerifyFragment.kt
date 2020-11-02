package com.example.iwen.jetpacknavigation.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.iwen.jetpacknavigation.R
import com.example.iwen.jetpacknavigation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_avatar_verify.view.*

/**
 * author : Iwen大大怪
 * create : 2020/11/2 22:04
 */
class AvatarVerifyFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_avatar_verify
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        rootView.avatarVerifyBtn.setOnClickListener {
            findNavController().navigate(R.id.to_login_page)
        }
    }
}