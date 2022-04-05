package com.dominic.codialuz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyMentorRecycleView
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Person
import kotlinx.android.synthetic.main.fragment_adding.view.*
import kotlin.reflect.typeOf


class AddingFragment : Fragment() {
    lateinit var root:View
   lateinit var MyKurs:ArrayList<Person>
   lateinit var myDbMentor: MyDbMentor
   lateinit var myMentorRecycleView: MyMentorRecycleView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root =  inflater.inflate(R.layout.fragment_adding, container, false)
        myDbMentor = MyDbMentor(requireContext())
        MyKurs = ArrayList()
        MyKurs = myDbMentor.getAllCourse()
        run()
        val backIcon:ImageView = root.findViewById(R.id.back_icon_2)
        val title:TextView = root.findViewById(R.id.myapp_name_icon_2)
        title.setText("Mentorlar")
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
       val type =  arguments?.getString("keyMe","Empty")
        return root
    }
    private fun run(){
        myMentorRecycleView = MyMentorRecycleView(requireContext(),MyKurs,object : MyMentorRecycleView.RvClick{
            override fun onClick(person: Person, imageView: ImageView) {
                findNavController().navigate(R.id.personsFragment, bundleOf("person" to person))
            }
        })

        root.mymentorrecycleview.adapter = myMentorRecycleView

    }
}