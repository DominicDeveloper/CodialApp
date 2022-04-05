package com.dominic.codialuz.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dominic.codialuz.CreatedFragment
import com.dominic.codialuz.CreatingGroups
import com.dominic.codialuz.GroupsFragment

class MyAdapterPager(val titleList:ArrayList<String>, fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
       return titleList.size
    }

    override fun createFragment(position: Int): Fragment {
       when(position){
           0 -> return CreatedFragment()
           1 -> return CreatingGroups()
       }
        return CreatedFragment()
    }

}