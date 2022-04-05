package com.dominic.codialuz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.fragment_delete_student.view.*


class DeleteStudentFragment : Fragment() {
   lateinit var root:View
   lateinit var myDbMentor: MyDbMentor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_delete_student, container, false)
        val tookSt = Hold.takeStudentforEdit as Student
        myDbMentor = MyDbMentor(requireContext())
        val iconBack:ImageView = root.findViewById(R.id.back_icon_2)
        iconBack.setOnClickListener {
            onDestroy()
            findNavController().popBackStack()
        }
        root.item_edit_talaba_name.setText(tookSt.student_name)
        root.item_edit_talaba_surname.setText(tookSt.student_surname)
        root.item_edit_talaba_number.setText(tookSt.student_number)
        root.item_edit_talaba_date.setText(tookSt.student_date)
        root.item_edit_talaba_save.setOnClickListener {
           // val student = Student(tookSt.student_id,root.item_edit_talaba_name.text.toString().trim(),root.item_edit_talaba_surname.text.toString().trim(),root.item_edit_talaba_number.text.toString().trim(),root.item_edit_talaba_date.text.toString().trim(),tookSt.student_group)
            tookSt.student_name = root.item_edit_talaba_name.text.toString().trim()
            tookSt.student_surname = root.item_edit_talaba_surname.text.toString().trim()
            tookSt.student_number = root.item_edit_talaba_number.text.toString().trim()
            tookSt.student_date = root.item_edit_talaba_date.text.toString().trim()
            myDbMentor.editStudent(tookSt)
            Toast.makeText(requireContext(), "O`zgartirildi", Toast.LENGTH_SHORT).show()
            onDestroy()
            findNavController().popBackStack()
        }

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}