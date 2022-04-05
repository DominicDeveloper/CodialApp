package com.dominic.codialuz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dominic.codialuz.Adapters.MyStudentRecycle
import com.dominic.codialuz.Constant.Hold
import com.dominic.codialuz.DataBase.MyDbMentor
import com.dominic.codialuz.Users.Group
import com.dominic.codialuz.Users.Student
import kotlinx.android.synthetic.main.fragment_finally.view.*
import kotlinx.android.synthetic.main.item_delete_student.view.*

class FinallyFragment : Fragment() {
   lateinit var root:View
   lateinit var myDbMentor: MyDbMentor
   lateinit var studentList:ArrayList<Student>
   lateinit var myStudentRecycle: MyStudentRecycle
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_finally, container, false)
        val iconBack:ImageView = root.findViewById(R.id.back_icon)
        val iconAdd:ImageView = root.findViewById(R.id.add_icon)
        val iconTitle:TextView = root.findViewById(R.id.myapp_name_icon)
        val key:Group = Hold.group!!
        myDbMentor = MyDbMentor(requireContext())
        studentList = ArrayList()
        syncStudent(key?.group_id!!)
        iconTitle.text  = key.group_name
        root.finally_group_name.text = key.group_name
        root.finally_group_student.text = "O`quchilar soni: ${studentList.size}"
        root.finally_group_vaqti.text = key.group_time
        root.finally_group_mentor_name.text = "Mentor: ${key.group_mentor?.Name}"
        val isVb = arguments?.getString("visibility")
        if (isVb == "false"){
            root.finally_group_start_lesson.visibility = View.GONE
        }else{
            root.finally_group_start_lesson.visibility = View.VISIBLE
        }

       // load()
        iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
        iconAdd.setOnClickListener {
            findNavController().navigate(R.id.addStudentFragment, bundleOf("group_info" to key))

        }

        root.finally_group_start_lesson.setOnClickListener {
            key.isopen = "1"
            if (studentList.isNotEmpty()){
                myDbMentor.startLesson(key)
                load()
                Hold.mygroup = key
                Toast.makeText(requireContext(), "Guruhga dars boshlandi", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack(R.id.groupsFragment,true)
            }else{
                Toast.makeText(requireContext(), "Guruhga dars boshlab bo`lmaydi o`quvchilar mavjud emas!", Toast.LENGTH_SHORT).show()
            }

        }



        return root
    }


    override fun onPause() {
        super.onPause()
        onDestroy()
    }

    private fun syncStudent(key:Int) {
        val list:ArrayList<Student> = myDbMentor.getAllStudent()
        if (list.isNotEmpty()){
            list.forEach {
                if (it.student_group?.group_id == key){
                    studentList.add(it)
                }
            }
            load()
        }
    }

    private fun load()
    {

        myStudentRecycle = MyStudentRecycle(requireContext(),studentList,object : MyStudentRecycle.RvClick{
            override fun onClickDelete(student: Student, cardView: CardView) {
                val alertDelete = AlertDialog.Builder(requireContext()).create()
                val setV = LayoutInflater.from(requireContext()).inflate(R.layout.item_delete_student,null,false)
                alertDelete.setView(setV)
                alertDelete.show()
                setV.item_delete_student_no.setOnClickListener {
                    alertDelete.dismiss()
                    Toast.makeText(requireContext(), "Bekor qilindi", Toast.LENGTH_SHORT).show()

                }
                setV.item_delete_student_yes.setOnClickListener {
                    myDbMentor.deleteOnlyStudent(student)
                    studentList.remove(student)
                    alertDelete.cancel()
                    Toast.makeText(requireContext(), "O`chirildi", Toast.LENGTH_SHORT).show()
                    load()
                }

            }

            override fun onClickEdit(student: Student, cardView: CardView) {
                Hold.takeStudentforEdit = student
                findNavController().navigate(R.id.deleteStudentFragment)
                onDestroy()
            }
        })
        root.mystudentsrecycle.adapter = myStudentRecycle
    }


}