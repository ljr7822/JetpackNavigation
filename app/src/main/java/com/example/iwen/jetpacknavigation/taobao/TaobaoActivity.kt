package com.example.iwen.jetpacknavigation.taobao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.iwen.jetpacknavigation.R
import kotlinx.android.synthetic.main.activity_taobao.*

/**
 * author : Iwen大大怪
 * create : 2020/11/4 14:02
 */
class TaobaoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taobao)

        initView()
    }

    private fun initView() {
        // 方法1：去fragment管理器找
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)

        // 方法2：把androidx.fragment.app.FragmentContainerView改成fragment
//        bottomNavigationView.setupWithNavController(findNavController(R.id.fragmentContainerView))
    }
}