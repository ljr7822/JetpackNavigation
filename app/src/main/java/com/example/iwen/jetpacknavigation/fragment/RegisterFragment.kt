package com.example.iwen.jetpacknavigation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.iwen.jetpacknavigation.R
import com.example.iwen.jetpacknavigation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.view.*

/**
 * 注册的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class RegisterFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        rootView.toAvatar.setOnClickListener {
            findNavController().navigate(R.id.to_verify_fragment)
        }
    }
}