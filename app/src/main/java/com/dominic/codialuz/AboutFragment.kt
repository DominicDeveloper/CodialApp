package com.dominic.codialuz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Users.Stream
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {
    lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_about, container, false)

        val info = arguments?.getString("keyAbout", "There is empty")

        root.text_about.text = info

        actionBar(Stream.CoursName.toString())

        return root
    }
    private fun actionBar(info:String){
        val backIcon: ImageView = root.findViewById(R.id.back_icon_2)
        val title: TextView = root.findViewById(R.id.myapp_name_icon_2)
        title.setText(info)

        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}