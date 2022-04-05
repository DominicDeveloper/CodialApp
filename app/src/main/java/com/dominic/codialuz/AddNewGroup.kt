package com.dominic.codialuz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Mentor
import com.dominic.codialuz.Users.Person
import kotlinx.android.synthetic.main.fragment_add_new_group.view.*

class AddNewGroup : Fragment() {
    lateinit var root:View
    lateinit var mentor:ArrayList<Mentor>
    lateinit var myDbMentor: MyDbMentor
    lateinit var mentorname:ArrayList<String>
    lateinit var saveMentor:Mentor
    lateinit var person: Person
    var lesson_time = arrayListOf("10:00 - 12:00","12:00 - 14:00","14:00 - 16:00","16:00 - 18:00","18:00 - 20:00")
    var lesson_day = arrayListOf("Dushanba/Chorshanba/Juma","Seshanba/Payshanba/Shanba")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_add_new_group, container, false)
        myDbMentor = MyDbMentor(requireContext())
        mentor = ArrayList()

        person = Hold.person!!
        mentor = myDbMentor.getAllMentor()
        mentorname = ArrayList()
        mentor.forEach {
           if (it.person?.curs_id == Hold.person?.curs_id){
               mentorname.add(it.Name!!)
           }

        }

        syncSpinerAdapter()
        root.spinnerfragment_add_group_add.setOnClickListener {
            val group_name:TextView = root.findViewById(R.id.fragment_add_group_name)
            val group_mentor:AutoCompleteTextView = root.findViewById(R.id.fragment_add_group_mentor)
            val group_time:AutoCompleteTextView = root.findViewById(R.id.fragment_Add_group_time)
            val group_date:AutoCompleteTextView = root.findViewById(R.id.fragment_Add_group_date)
            if (group_name.text.isNotEmpty() && group_mentor.text.isNotEmpty() && group_time.text.isNotEmpty() && group_date.text.isNotEmpty()){
                myDbMentor.addGroup(Group(group_name.text.toString(),check(group_mentor.text.toString().trim()),group_time.text.toString(),group_date.text.toString(),"0",person))
                Hold.group = Group(group_name.text.toString(),check(group_mentor.text.toString().trim()),group_time.text.toString(),group_date.text.toString(),"0",person)
                Toast.makeText(requireContext(), "Guruh yaratildi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.addNewGroup,true)
                findNavController().navigate(R.id.groupsFragment)

            }
        }

        return root
    }
    private fun syncSpinerAdapter(){
        val adapter_mentor = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,mentorname)
        val adapter_time = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,lesson_time)
        val adapter_day = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,lesson_day)
        root.fragment_add_group_mentor.setAdapter(adapter_mentor)
        root.fragment_Add_group_time.setAdapter(adapter_time)
        root.fragment_Add_group_date.setAdapter(adapter_day)
    }
    private fun check(name:String):Mentor{
        mentor.forEach {
            if (it.Name == name){
                saveMentor = Mentor(it.id,it.Name,it.Surname,it.Number,it.person!!)
            }else{
                saveMentor = Mentor(it.id,it.Name,it.Surname,it.Number,it.person!!)
            }
        }
        return saveMentor
    }

    override fun onPause() {
        super.onPause()
        onDestroy()
    }

}