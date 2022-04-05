package com.dominic.codialuz

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.fragment_add_student.view.*

class AddStudentFragment : Fragment() {
    lateinit var root:View
    lateinit var listStudent:ArrayList<Student>
    lateinit var myDbMentor: MyDbMentor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_add_student, container, false)
        listStudent = ArrayList()
        myDbMentor = MyDbMentor(requireContext())
        val keyinfo = arguments?.getSerializable("group_info") as Group
        val iconBack:ImageView = root.findViewById(R.id.back_icon_2)
        iconBack.setOnClickListener {
            onDestroy()
            findNavController().popBackStack()
        }
        root.item_add_talaba_add.setOnClickListener {
           // val student = Student(root.item_add_talaba_name.text.toString().trim(),root.item_add_talaba_surname.text.toString().trim(),root.item_add_talaba_number.text.toString().trim(),root.item_add_talaba_date.text.toString().trim(),keyinfo)
            myDbMentor.addStudent(Student(root.item_add_talaba_name.text.toString().trim(),root.item_add_talaba_surname.text.toString().trim(),root.item_add_talaba_number.text.toString().trim(),root.item_add_talaba_date.text.toString().trim(),keyinfo))
            Toast.makeText(requireContext(), "Student qo`shildi!", Toast.LENGTH_SHORT).show()
            onDestroy()
            findNavController().popBackStack(R.id.finallyFragment,false)

        }




        return root
    }

    override fun onPause() {
        super.onPause()
        onDestroy()
    }


}