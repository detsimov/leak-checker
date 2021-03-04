package com.detsimov.leakchecker.ui_android.features.main_navigation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.detsimov.leakchecker.ui_android.features.stack.StackFragment
import com.detsimov.leakchecker.ui_android.features.stack.models.StackScreen

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    val items = StackScreen.values().map { StackFragment.create(it) }


    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

}

