package com.dominic.codialuz

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dominic.codialuz.Adapters.MyAdapterPager
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.Users.Person
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GroupsFragment : Fragment() {
   lateinit var root:View
   lateinit var tabLayout: TabLayout
   lateinit var viewPager2: ViewPager2
   lateinit var titleList:ArrayList<String>
   lateinit var myAdapterPager: MyAdapterPager
   lateinit var mentorsData:ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_groups, container, false)
        titleList = ArrayList()
        mentorsData = ArrayList()
        val save = Hold.person
        Hold.person_2  = save
        mentorsAdding()
        val iconAdd:ImageView = root.findViewById(R.id.add_icon)
        iconAdd.isVisible = false
        loadTitleData()
        tabLayout = root.findViewById(R.id.mygroup_tablayout)
        viewPager2 = root.findViewById(R.id.mygroup_viewpager2)
        myAdapterPager = MyAdapterPager(titleList,requireActivity())
        val title = Hold.tiitle
        val themeName:TextView = root.findViewById(R.id.myapp_name_icon)
        themeName.text = title

        viewPager2.adapter = myAdapterPager
        TabLayoutMediator(tabLayout,viewPager2,(TabLayoutMediator.TabConfigurationStrategy { tab, position ->  tab.setText(titleList[position])})).attach()


        table()

        return root
    }

    private fun loadTitleData() {
        titleList.add("Ochilgan guruhlar")
        titleList.add("Ochilayotgan guruhlar")
    }
    private fun table(){
        val iconBack:ImageView = root.findViewById(R.id.back_icon)
        val iconAdd:ImageView = root.findViewById(R.id.add_icon)
        iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
        iconAdd.setOnClickListener {
            findNavController().popBackStack(R.id.groupsFragment,true)
            findNavController().navigate(R.id.addNewGroup)

        }

        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> {
                        iconAdd.isVisible = false
                    }
                    1 -> {
                        iconAdd.isVisible = true
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
               
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                
            }
        })

    }
    private fun mentorsAdding(){
        mentorsData.add("Dominic Azimov")

    }
    override fun onPause() {
        super.onPause()
        onDestroy()
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


}