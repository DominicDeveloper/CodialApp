package com.dominic.codialuz

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyTypeReciycleView
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Person
import com.dominic.codialuz.Users.Stream
import kotlinx.android.synthetic.main.add_course.view.*
import kotlinx.android.synthetic.main.fragment_types.view.*


class TypesFragment : Fragment() {
    lateinit var root: View
    lateinit var myTypeReciycleView: MyTypeReciycleView
    lateinit var WhichType:ArrayList<Person>
    lateinit var myDbMentor: MyDbMentor
    lateinit var title:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        WhichType = ArrayList()
        myDbMentor = MyDbMentor(requireContext())
        WhichType = myDbMentor.getAllCourse()
        root = inflater.inflate(R.layout.fragment_types, container, false)

        Update()
       title = root.findViewById(R.id.myapp_name_icon)
        title.text = "Barcha kurslar"

        val key = arguments?.getString("keyMe", "Default") // boshqa fragmentdan kelayotgan malumot

        scanMe(key!!)


        return root
    }

    private fun scanMe(key: String) { // malumot qiymatini tekshirish
        when (key) {
            "group" -> {Toast.makeText(requireContext(), "Group", Toast.LENGTH_SHORT).show()
            putAdapter("group") }
            "mentor" -> {Toast.makeText(requireContext(), "Mentor", Toast.LENGTH_SHORT).show()
            putAdapter("mentor") }
            "kurs" -> {Toast.makeText(requireContext(), "Kurs", Toast.LENGTH_SHORT).show()
            putAdapter("kurs")
            title.text = "Kurslar"}
        }

    }
    private fun putAdapter(whichAdapterIs:String){
        when(whichAdapterIs){
            "group" ->{

                Group() // go Group function
            }
            "mentor" ->{

            }
            "kurs" ->{

                Add_Kurs_customBar() // go custombar function
            }
        }

    }
    private fun Add_Kurs_customBar(){
        val backIcon:ImageView = root.findViewById(R.id.back_icon)
        val addIcon:ImageView = root.findViewById(R.id.add_icon)


        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        addIcon.setOnClickListener {
            val myDialog = AlertDialog.Builder(requireContext()).create()
            val myItem = LayoutInflater.from(requireContext()).inflate(R.layout.add_course,null,false)
            myDialog.setView(myItem)
            myDialog.show()
            myItem.item_edt_add.setOnClickListener {
                if (myItem.item_edt_add_coursename.text.isNotEmpty() && myItem.item_edt_add_courseabout.text.isNotEmpty()){
                    WhichType.add(Person(myItem.item_edt_add_coursename.text.toString(),myItem.item_edt_add_courseabout.text.toString()))
                    val person = Person(myItem.item_edt_add_coursename.text.toString(),myItem.item_edt_add_courseabout.text.toString())
                    myDbMentor.addCourse(person)
                    Update()
                    myDialog.cancel()

                }else if (myItem.item_edt_add_coursename.text.isEmpty()){
                    myItem.item_edt_add_coursename.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                }else if(myItem.item_edt_add_courseabout.text.isEmpty()){
                    myItem.item_edt_add_courseabout.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                }
            }
            myItem.item_edt_close.setOnClickListener {

                myDialog.dismiss()
            }

        }



    }
    private fun Group(){
        val backIcon:ImageView = root.findViewById(R.id.back_icon)
        val addIcon:ImageView = root.findViewById(R.id.add_icon)
        addIcon.isVisible = false // it is working...

        backIcon.setOnClickListener {
            findNavController().popBackStack() // for leave this fragment
        }

        myTypeReciycleView = MyTypeReciycleView(requireContext(),WhichType,object : MyTypeReciycleView.RvClick{
            override fun onClick(person: Person, imageView: ImageView) {
                Hold.person = person
                Hold.tiitle = person.curs_name
                findNavController().navigate(R.id.groupsFragment)

            }
        })
        root.my_type_recycleview.adapter = myTypeReciycleView



    }
    private fun Update(){

      myTypeReciycleView = MyTypeReciycleView(requireContext(),WhichType,object : MyTypeReciycleView.RvClick{
          override fun onClick(person: Person, imageView: ImageView) {
             findNavController().navigate(R.id.aboutFragment, bundleOf("keyAbout" to person.curs_about))

              Stream.CoursName = person.curs_name
          }
      })
        WhichType = myDbMentor.getAllCourse()
        root.my_type_recycleview.adapter = myTypeReciycleView
    }

    override fun onResume() {
        super.onResume()


    }



}
