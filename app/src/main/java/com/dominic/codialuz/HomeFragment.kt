package com.dominic.codialuz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
       root.btn_guruhlar.setOnClickListener {
           val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_click)
           root.btn_guruhlar.startAnimation(anim)
           val key = "group"
          root.findNavController().navigate(R.id.typesFragment, bundleOf("keyMe" to key))
       }

        root.btn_kurslar.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_click)
            root.btn_kurslar.startAnimation(anim)
            val key = "kurs"
            root.findNavController().navigate(R.id.typesFragment, bundleOf("keyMe" to key))
        }
        root.btn_mentorlar.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_click)
            root.btn_mentorlar.startAnimation(anim)
            val key = "mentor"
            root.findNavController().navigate(R.id.addingFragment, bundleOf("keyMe" to key))
        }

        return root
    }
}